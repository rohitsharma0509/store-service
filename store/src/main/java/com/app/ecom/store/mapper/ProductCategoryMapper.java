package com.app.ecom.store.mapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.app.ecom.store.dto.masterdata.ProductCategoryDto;
import com.app.ecom.store.model.ProductCategory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ProductCategoryMapper {
	
	public Set<ProductCategoryDto> productCategoriesToProductCategoryDtos(Set<ProductCategory> productCategories) {
		
		if(CollectionUtils.isEmpty(productCategories)) {
			return Collections.emptySet();
		}
		
		Set<ProductCategoryDto> productCategoryDtos = new HashSet<>();
		productCategories.stream().forEach(productCategory -> productCategoryDtos.add(productCategoryToProductCategoryDto(productCategory)));
		return productCategoryDtos;
	}

	
	public ProductCategoryDto productCategoryToProductCategoryDto(ProductCategory productCategory) {
		if(null == productCategory) {
			return null;
		}
		
		ProductCategoryDto productCategoryDto = new ProductCategoryDto();
		productCategoryDto.setId(productCategory.getId());
		productCategoryDto.setName(productCategory.getName());
		return productCategoryDto;
	}
	
	public Set<ProductCategory> productCategoryDtosToProductCategories(Set<ProductCategoryDto> productCategoryDtos) {
		
		if(CollectionUtils.isEmpty(productCategoryDtos)) {
			return Collections.emptySet();
		}
		
		Set<ProductCategory> productCategories = new HashSet<>();
		productCategoryDtos.stream().forEach(productCategoryDto -> productCategories.add(productCategoryDtoToProductCategory(productCategoryDto)));
		return productCategories;
	}

	
	public ProductCategory productCategoryDtoToProductCategory(ProductCategoryDto productCategoryDto) {
		if(null == productCategoryDto) {
			return null;
		}
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(productCategoryDto.getId());
		productCategory.setName(productCategoryDto.getName());
		return productCategory;
	}
}
