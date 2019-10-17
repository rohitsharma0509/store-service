package com.app.ecom.store.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.OrderDto;
import com.app.ecom.store.dto.ProductDto;
import com.app.ecom.store.mapper.OrderMapper;
import com.app.ecom.store.model.Order;
import com.app.ecom.store.model.User;
import com.app.ecom.store.querybuilder.QueryBuilder;
import com.app.ecom.store.repository.OrderRepository;
import com.app.ecom.store.service.AddressService;
import com.app.ecom.store.service.OrderService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private QueryBuilder queryBuilder;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private AddressService addressService;
	
	public OrderDto addOrder(java.util.List<ProductDto> productDtos, User user, Double totalPrice, Long addressId) {
		Order order = orderRepository.save(orderMapper.convertToOrder(productDtos, user, totalPrice, addressService.getAddressById(addressId)));
		return orderMapper.orderToOrderDto(order);
	}

	@Override
	public OrderDto getOrder(Long id) {
	    Optional<Order> optional = orderRepository.findById(id);
	    if(optional.isPresent()) {
	        return orderMapper.orderToOrderDto(optional.get());
	    } else {
	        return null;
	    }
	}

	@Override
	public Page<Order> getOrders(Pageable pageable) {
		PageRequest request = PageRequest.of(pageable.getPageNumber() - 1,
				pageable.getPageSize(), pageable.getSort());
		return orderRepository.findAll(request);
	}
	
	@Override
	public Long countByOrderDate(ZonedDateTime orderDate){
		return orderRepository.countByOrderDate(orderDate);
	}
	
	@Override
	public Long countByOrderDateGreaterThanEqual(ZonedDateTime orderDate){
		return orderRepository.countByOrderDateGreaterThanEqual(orderDate);
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
			
			Phrase customerPhrase = new Phrase("", normalFont);
			customerPhrase.add("Order Number: "+ orderDto.getOrderNumber());
			customerPhrase.add("\n");
			customerPhrase.add(orderDto.getUserDto().getFirstName()+" "+orderDto.getUserDto().getLastName());
			customerPhrase.add("\n");
			customerPhrase.add(orderDto.getAddressDto().getAddressLine1());
			customerPhrase.add("\n");
			customerPhrase.add(orderDto.getAddressDto().getAddressLine2());
			customerPhrase.add("\n");
			customerPhrase.add(orderDto.getAddressDto().getCity()+", "+orderDto.getAddressDto().getState()+", "+orderDto.getAddressDto().getPincode());
			customerPhrase.add("\n");
			customerPhrase.add("Email: "+orderDto.getUserDto().getEmail());
			customerPhrase.add("\n");
			customerPhrase.add("Mobile: "+orderDto.getUserDto().getMobile());

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			PdfPCell cell = new PdfPCell(customerPhrase);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			
			Phrase datePhrase = new Phrase(orderDto.getOrderDate(), normalFont);

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
			for(ProductDto productDto : orderDto.getProductDtos()) {
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
				
				cell = new PdfPCell(new Phrase(productDto.getQuantity()+"", normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase((productDto.getPerProductPrice()*productDto.getQuantity())+"", normalFont));
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
			e.printStackTrace();
		}
		return baos;
	}
	
	@Override
	public CustomPage<OrderDto> searchOrders(String orderNumber, String fromDate, String toDate, Long userId, Pageable pageable) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		StringBuilder query = new StringBuilder("select * from orders where 1=1");
		StringBuilder countQuery = new StringBuilder("select count(order_id) from orders where 1=1");
		if(!StringUtils.isEmpty(orderNumber)) {
			query.append(" and order_number like '%"+orderNumber+"%'");
			countQuery.append(" and order_number like '%"+orderNumber+"%'");
		}
		if(!StringUtils.isEmpty(fromDate)) {
			query.append(" and date(order_date)<='"+commonUtil.formatDate(fromDate, Constants.DD_MM_YYYY, Constants.YYYY_MM_DD)+"'");
			countQuery.append(" and date(order_date)<='"+commonUtil.formatDate(fromDate, Constants.DD_MM_YYYY, Constants.YYYY_MM_DD)+"'");
		}
		if(!StringUtils.isEmpty(toDate)) {
			query.append(" and date(order_date)>='"+commonUtil.formatDate(fromDate, Constants.DD_MM_YYYY, Constants.YYYY_MM_DD)+"'");
			countQuery.append(" and date(order_date)>='"+commonUtil.formatDate(fromDate, Constants.DD_MM_YYYY, Constants.YYYY_MM_DD)+"'");
		}
		if(!StringUtils.isEmpty(userId)) {
			query.append(" and user_id="+userId);
			countQuery.append(" and user_id="+userId);
		}
		query.append(" limit "+offset+", "+limit);
		List<Tuple> tuples = queryBuilder.getTupleByQuery(query.toString(), null);
		
		List<OrderDto> orderDtos = new ArrayList<>();
		for(Tuple tuple : tuples){
			OrderDto orderDto = new OrderDto();
			orderDto.setId(Long.parseLong(String.valueOf(tuple.get("order_id"))));
			orderDto.setOrderDate(commonUtil.formatDate(String.valueOf(tuple.get("order_date")), Constants.YYYY_MM_DD, Constants.DD_MM_YYYY));
			orderDto.setOrderNumber(String.valueOf(tuple.get("order_number")));
			orderDto.setTotalAmount(Double.parseDouble(String.valueOf(tuple.get("total_amount"))));
			orderDtos.add(orderDto);
		}
		
		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), null);
		
		CustomPage<OrderDto> page = new CustomPage<>();
		page.setContent(orderDtos);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

	@Override
	public Long countOrderByProductIdIn(List<Long> ids) {
		return orderRepository.countOrderByProductIdIn(ids);
	}
}