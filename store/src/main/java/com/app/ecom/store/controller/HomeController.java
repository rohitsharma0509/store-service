package com.app.ecom.store.controller;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.service.OrderService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.util.ChartGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ChartGenerator chartGenerator;
	
	@GetMapping(value = { "/", RequestUrls.HOME })
	public String home(Model model) {
		Long totalProducts = productService.getNumberOfProducts();
		Long outOfStockProduct = productService.getOutOfStockProductQuantity();
		Long alertProducts = productService.getAlertProductQuantity();
		
		model.addAttribute(FieldNames.TOTAL_PRODUCTS, totalProducts);
		model.addAttribute(FieldNames.ALERT_PRODUCTS, alertProducts);
		model.addAttribute(FieldNames.OUT_OF_STOCK_PRODUCTS, outOfStockProduct);
		model.addAttribute(FieldNames.AVAILABLE_PRODUCTS, totalProducts - outOfStockProduct);
		model.addAttribute(FieldNames.TODAY_ORDER, orderService.countByOrderDateGreaterThanEqual(ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS)));
		model.addAttribute(FieldNames.STOCK_STATUS, Base64.getEncoder().encodeToString(chartGenerator.createPieChart(alertProducts, (totalProducts - outOfStockProduct), outOfStockProduct)));
		model.addAttribute(FieldNames.YEARLY_SALES_GRAPH, Base64.getEncoder().encodeToString(chartGenerator.createLineChart()));
		model.addAttribute(FieldNames.MONTHLY_SALES_GRAPH, Base64.getEncoder().encodeToString(chartGenerator.createBarChart()));
		model.addAttribute("compareGraph", Base64.getEncoder().encodeToString(chartGenerator.createCategoryChart())); 
		return "home";
	}
}
