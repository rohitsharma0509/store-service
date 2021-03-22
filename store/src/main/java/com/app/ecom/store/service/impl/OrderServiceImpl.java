package com.app.ecom.store.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.app.ecom.store.client.OrderServiceClient;
import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.OrderByClause;
import com.app.ecom.store.dto.orderservice.OrderDetailDto;
import com.app.ecom.store.dto.orderservice.OrderDto;
import com.app.ecom.store.dto.orderservice.OrderDtos;
import com.app.ecom.store.dto.orderservice.OrderSearchRequest;
import com.app.ecom.store.dto.productservice.ProductDto;
import com.app.ecom.store.dto.userservice.AddressDto;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.enums.SortOrder;
import com.app.ecom.store.service.AddressService;
import com.app.ecom.store.service.OrderService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.service.UserService;
import com.app.ecom.store.util.CommonUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private OrderServiceClient orderServiceClient;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ProductService productService;
	
	public OrderDto addOrder(java.util.List<OrderDetailDto> orderDetailDtos, UserDto userDto, Double totalPrice, Long addressId) {
		OrderDto orderDto = new OrderDto();
		orderDto.setAddressId(addressId);
		orderDto.setOrderDetailDtos(orderDetailDtos);
		orderDto.setTotalAmount(totalPrice);
		orderDto.setUserId(userDto.getId());
		orderDto.setCreatedBy(userDto.getUsername());
		orderDto.setLastModifiedBy(userDto.getUsername());
		return orderServiceClient.createUpdateOrder(orderDto);
	}

	@Override
	public OrderDto getOrder(Long id) {
		OrderSearchRequest orderSearchRequest = new OrderSearchRequest();
		orderSearchRequest.setOrderId(id);
		OrderDtos orderDtos = orderServiceClient.getOrders(orderSearchRequest);
		if(null != orderDtos && !CollectionUtils.isEmpty(orderDtos.getOrders())) {
			Optional<OrderDto> optional = orderDtos.getOrders().stream().filter(Objects::nonNull).findFirst();
			OrderDto orderDto = optional.isPresent() ? optional.get() : null;
			for(OrderDetailDto orderDetailDto : orderDto.getOrderDetailDtos()) {
				ProductDto productDto = productService.getProductById(orderDetailDto.getProductId());
				orderDetailDto.setName(productDto.getName());
				orderDetailDto.setCode(productDto.getCode());
				orderDetailDto.setPerProductPrice(productDto.getPerProductPrice());
			}
			return orderDto;
		} else {
			return null;
		}
	}

	@Override
	public CustomPage<OrderDto> getOrders(Pageable pageable, Map<String, String> params) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		
		OrderSearchRequest orderSearchRequest = new OrderSearchRequest();
		orderSearchRequest.setOffset(offset);
		orderSearchRequest.setLimit(limit);
		orderSearchRequest.setFromDate(commonUtil.convertDateToZonedDateTime(commonUtil.convertStringToDate(params.get(FieldNames.FROM_DATE), Constants.YYYY_MM_DD)));
		orderSearchRequest.setToDate(commonUtil.convertDateToZonedDateTime(commonUtil.convertStringToDate(params.get(FieldNames.TO_DATE), Constants.YYYY_MM_DD)));
		orderSearchRequest.setOrderNumber(params.get(FieldNames.ORDER_NUMBER));
		orderSearchRequest.setUserId(StringUtils.isEmpty(params.get(FieldNames.USER_ID)) ? null : Long.parseLong(params.get(FieldNames.USER_ID)));
		List<OrderByClause> orderByClauses = new ArrayList<>();
		OrderByClause orderByClause = new OrderByClause();
		orderByClause.setSortBy(FieldNames.CREATED_TS);
		orderByClause.setSortOrder(SortOrder.DESC);
		orderByClauses.add(orderByClause);
		orderSearchRequest.setOrderByClauses(orderByClauses);
		
		OrderDtos orderDtos = orderServiceClient.getOrders(orderSearchRequest);
		Long totalRecords = orderServiceClient.countOrders(orderSearchRequest);
		CustomPage<OrderDto> page = new CustomPage<>();
		page.setContent(orderDtos == null ? Collections.emptyList() : orderDtos.getOrders());
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages(totalRecords==null ? 0 : (int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}
	
	@Override
	public Long getNumberOfOrdersByDateAndUserId(ZonedDateTime orderDate, Long userId){
		OrderSearchRequest orderSearchRequest = new OrderSearchRequest();
		orderSearchRequest.setFromDate(orderDate);
		orderSearchRequest.setUserId(userId);
		return orderServiceClient.countOrders(orderSearchRequest);
	}
	
	public ByteArrayOutputStream createOrderPdf(Long id){
		OrderDto orderDto = getOrder(id);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try  {
			Font normalFont = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
			Font boldFont = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
			
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, baos);
			document.open();
			
			UserDto userDto = getUserDtoByUserId(orderDto.getUserId());
			AddressDto addressDto = getAddressDtoByAddressId(orderDto.getAddressId());
			Phrase customerPhrase = new Phrase("", normalFont);
			customerPhrase.add("Order Number: "+ orderDto.getOrderNumber());
			customerPhrase.add("\n");
			customerPhrase.add(userDto.getFirstName()+" "+userDto.getLastName());
			customerPhrase.add("\n");
			customerPhrase.add(addressDto.getAddressLine1());
			customerPhrase.add("\n");
			customerPhrase.add(addressDto.getAddressLine2());
			customerPhrase.add("\n");
			customerPhrase.add(addressDto.getCity()+", "+addressDto.getState()+", "+addressDto.getPincode());
			customerPhrase.add("\n");
			customerPhrase.add("Email: "+userDto.getEmail());
			customerPhrase.add("\n");
			customerPhrase.add("Mobile: "+userDto.getMobile());

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			PdfPCell cell = new PdfPCell(customerPhrase);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			
			Phrase datePhrase = new Phrase(commonUtil.convertZonedDateTimeToString(orderDto.getCreatedTs(), Constants.DATETIME_FORMAT_YYYYMMDDHHMMSS), normalFont);

			cell = new PdfPCell(datePhrase);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("", normalFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("", normalFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			document.add(table);
			
			table = new PdfPTable(6);
			table.setWidthPercentage(100);
			
			cell = new PdfPCell(new Phrase("#", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Item", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Description", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Unit Cost", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Qty", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			int count = 0;
			for(OrderDetailDto orderDetailDto : orderDto.getOrderDetailDtos()) {
				ProductDto productDto = productService.getProductById(orderDetailDto.getProductId());
				
				cell = new PdfPCell(new Phrase(count+1+"",normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);				
				
				cell = new PdfPCell(new Phrase(productDto.getName(), normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(productDto.getCode(), normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(productDto.getPerProductPrice()+"", normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(orderDetailDto.getQuantity()+"", normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase((productDto.getPerProductPrice()*orderDetailDto.getQuantity())+"", normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
			}
			
			cell = new PdfPCell(new Phrase("Subtotal", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setFixedHeight(30);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(orderDto.getTotalAmount().toString(), normalFont));
            cell.setFixedHeight(30);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(5);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Tax", boldFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(30);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("0.0", normalFont));
            cell.setFixedHeight(30);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(5);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Total", boldFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(30);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(orderDto.getTotalAmount().toString(), normalFont));
            cell.setFixedHeight(30);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(5);
            table.addCell(cell);
			
			document.add(table);
			document.close();
		} catch (Exception e) {
			logger.error("Exception while generating pdf.", e);
		}
		return baos;
	}

	@Override
	public Long countOrderByProductIdIn(List<Long> ids) {
		OrderSearchRequest orderSearchRequest = new OrderSearchRequest();
		orderSearchRequest.setProductIds(ids);
		return orderServiceClient.countOrders(orderSearchRequest);
	}

	@Override
	public UserDto getUserDtoByUserId(Long userId) {
		return userService.findUserById(userId);
	}

	@Override
	public AddressDto getAddressDtoByAddressId(Long addressId) {
		return addressService.getAddressById(addressId);
	}
}