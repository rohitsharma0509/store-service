package com.app.ecom.store.masterdata.service;

import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDtos;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategorySearchRequest;

public interface ProductCategoryService {
	
	ProductCategoryDto addUpdateProductCategory(ProductCategoryDto productCategoryDto);
	
	Long countProductCategories(ProductCategoryDto productCategoryDto);

	ProductCategoryDtos getProductCategories(ProductCategorySearchRequest productCategorySearchRequest);
	
	void deleteCategories(IdsDto idsDto);
}
