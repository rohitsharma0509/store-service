package com.app.ecom.store.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.ExcelData;
import com.app.ecom.store.dto.StockDto;
import com.app.ecom.store.model.Order;
import com.app.ecom.store.repository.OrderRepository;
import com.app.ecom.store.service.ExcelService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.util.ExcelUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Autowired
	private ExcelUtil excelUtil;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public ByteArrayOutputStream getExcelAsBytes(String reportName) {
		ExcelData excelData = new ExcelData();
		if(Constants.STOCK.equals(reportName)){
			CustomPage<StockDto> page = productService.getStockDetails(null, null, true);
			List<StockDto> stockDtos = page.getContent();
			List<List<String>> rows = new ArrayList<>();
			for(StockDto stockDto : stockDtos){
				List<String> values = new ArrayList<>();
				values.add(stockDto.getCategoryName());
				values.add(stockDto.getBrandName());
				values.add(stockDto.getName());
				values.add(String.valueOf(stockDto.getTotalQty()));
				values.add(String.valueOf(stockDto.getOrderedQty()));
				values.add(String.valueOf(stockDto.getAvailableQty()));
				rows.add(values);
			}
			
			List<String> headers = new ArrayList<>();
			headers.add("Category Name");
			headers.add("Brand Name");
			headers.add("Product Name");
			headers.add("Total Qty");
			headers.add("Ordered Qty");
			headers.add("Available Qty");
			
			excelData.setSheetName(Constants.STOCK);
			excelData.setHeaders(headers);
			excelData.setRows(rows);
		} else if(Constants.ORDERS.equals(reportName)){
			List<Order> orders = orderRepository.findAll();
			List<List<String>> rows = new ArrayList<>();
			for(Order order : orders){
				List<String> values = new ArrayList<>();
				values.add(order.getOrderNumber());
				values.add(commonUtil.convertZonedDateTimeToString(order.getOrderDate(), Constants.DD_MM_YYYY));
				values.add(String.valueOf(order.getTotalAmount()));
				rows.add(values);
			}
			excelData.setSheetName(Constants.ORDERS);
			List<String> headers = new ArrayList<>();
			headers.add("Order Number");
			headers.add("Order Date");
			headers.add("Amount");
			excelData.setHeaders(headers);
			excelData.setRows(rows);
		}
		return excelUtil.getExcelAsBytes(excelData);
	}
	
	@Override
	public ByteArrayOutputStream getFileAsBytes(String fileType) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			if("csv".equalsIgnoreCase(fileType)) {
				is = ExcelServiceImpl.class.getClassLoader().getResourceAsStream("sample/products.csv");
			}  else if("xml".equalsIgnoreCase(fileType)) {
				is = ExcelServiceImpl.class.getClassLoader().getResourceAsStream("sample/products.xml");
			}
			IOUtils.copy(is, baos);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return baos;
	}
}