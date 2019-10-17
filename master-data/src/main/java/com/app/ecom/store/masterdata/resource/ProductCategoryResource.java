package com.app.ecom.store.masterdata.resource;

import com.app.ecom.store.masterdata.constants.Endpoint;
import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDtos;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategorySearchRequest;
import com.app.ecom.store.masterdata.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCategoryResource {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@PutMapping(value = Endpoint.CATEGORY)
	public ResponseEntity<ProductCategoryDto> addUpdateProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
		try {
			ProductCategoryDto createdProductCategoryDto = productCategoryService.addUpdateProductCategory(productCategoryDto);
			return new ResponseEntity<>(createdProductCategoryDto, productCategoryDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.CATEGORY)
	public ResponseEntity<ProductCategoryDtos> getAllProductCategories(@RequestBody ProductCategorySearchRequest productCategorySearchRequest) {
		try {
			ProductCategoryDtos productCategoryDtos = productCategoryService.getProductCategories(productCategorySearchRequest);
			return new ResponseEntity<>(productCategoryDtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_CATEGORY)
	public ResponseEntity<Long> countProductCategories(@RequestBody ProductCategoryDto productCategoryDto) {
		try {
			Long noOfProductCategories = productCategoryService.countProductCategories(productCategoryDto);
			return new ResponseEntity<>(noOfProductCategories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.CATEGORY)
	public ResponseEntity<Void> deleteCategories(@RequestBody IdsDto idsDto) {
		try {
			productCategoryService.deleteCategories(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}