package com.app.ecom.store.service;

import java.util.List;
import java.util.Map;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.masterdata.ProductCategoryDto;
import org.springframework.data.domain.Pageable;

public interface ProductCategoryService {
	
	List<ProductCategoryDto> getAllProductCategories();
	
	ProductCategoryDto getProductCategoryByIdOrName(Long id, String name);

	ProductCategoryDto addCategory(ProductCategoryDto productCategory);

	CustomPage<ProductCategoryDto> getCategories(Pageable pageable, Map<String, String> params);

	void deleteCategories(IdsDto idsDto);
}
