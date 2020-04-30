package com.app.ecom.store.masterdata.service;

import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDtos;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategorySearchRequest;

public interface ProductCategoryService {
	
	ProductCategoryDto addUpdateProductCategory(ProductCategoryDto productCategoryDto);
	
	Long countProductCategories(ProductCategoryDto productCategoryDto);
	
	ProductCategoryDto getProductCategoryById(Long id);

	ProductCategoryDtos getProductCategories(ProductCategorySearchRequest productCategorySearchRequest);
	
	void deleteProductCategoryById(Long id);
	
	void deleteProductCategories(IdsDto idsDto);

}
