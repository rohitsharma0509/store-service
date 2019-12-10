package com.app.ecom.store.controller;

import java.util.HashMap;
import java.util.Map;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.productservice.StockDto;
import com.app.ecom.store.service.ProductCategoryService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@GetMapping(value = RequestUrls.STOCK)
	public String getStock(Model model,
			@RequestParam(required = false) String categoryId,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String productName,
			@PageableDefault(page = 1, size = 10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.CATEGORY_ID, categoryId);
		params.put(FieldNames.BRAND_NAME, brandName);
		params.put(FieldNames.PRODUCT_NAME, productName);
		CustomPage<StockDto> page = productService.getStockDetails(pageable, params);
		model.addAttribute(FieldNames.PAGE, page);
		model.addAttribute(FieldNames.CATEGORIES, productCategoryService.getAllProductCategories());
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.STOCK, page.getPageNumber()+1, page.getTotalPages(), params));
		return View.STOCK;
	}
}
