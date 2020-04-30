package com.app.ecom.store.masterdata.resource;

import com.app.ecom.store.masterdata.constants.Endpoint;
import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDtos;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategorySearchRequest;
import com.app.ecom.store.masterdata.service.ProductCategoryService;
import com.app.ecom.store.masterdata.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCategoryResource {
	
	private static final Logger logger = LogManager.getLogger(ProductCategoryResource.class);
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.CATEGORY)
	public ResponseEntity<ProductCategoryDto> addUpdateProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
		try {
			ProductCategoryDto createdProductCategoryDto = productCategoryService.addUpdateProductCategory(productCategoryDto);
			return new ResponseEntity<>(createdProductCategoryDto, productCategoryDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while adding product category: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = Endpoint.CATEGORY_WITH_ID)
	public ResponseEntity<ProductCategoryDto> getProductCategoryById(@PathVariable Long id) {
		try {
			ProductCategoryDto productCategoryDto = productCategoryService.getProductCategoryById(id);
			return new ResponseEntity<>(productCategoryDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting privilege: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.CATEGORY)
	public ResponseEntity<ProductCategoryDtos> getProductCategories(@RequestBody ProductCategorySearchRequest productCategorySearchRequest) {
		try {
			ProductCategoryDtos productCategoryDtos = productCategoryService.getProductCategories(productCategorySearchRequest);
			return new ResponseEntity<>(productCategoryDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting product categories: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_CATEGORY)
	public ResponseEntity<Long> countProductCategories(@RequestBody ProductCategoryDto productCategoryDto) {
		try {
			Long noOfProductCategories = productCategoryService.countProductCategories(productCategoryDto);
			return new ResponseEntity<>(noOfProductCategories, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting product category: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.CATEGORY_WITH_ID)
	public ResponseEntity<Void> deleteProductCategoryById(@PathVariable Long id) {
		try {
			productCategoryService.deleteProductCategoryById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting product category: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.CATEGORY)
	public ResponseEntity<Void> deleteProductCategories(@RequestBody IdsDto idsDto) {
		try {
			productCategoryService.deleteProductCategories(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting product categories: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}