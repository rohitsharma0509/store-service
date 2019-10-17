package com.app.ecom.store.masterdata.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.app.ecom.store.masterdata.dto.WhereClause;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDtos;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategorySearchRequest;
import com.app.ecom.store.masterdata.enums.OperationType;
import com.app.ecom.store.masterdata.model.ProductCategory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class ProductCategoryMapper {
	
	public ProductCategoryDtos productCategoriesToProductCategoryDtos(List<ProductCategory> productCategories) {
		ProductCategoryDtos productCategoryDtos = new ProductCategoryDtos();
		List<ProductCategoryDto> listOfProductCategoryDto = new ArrayList<>();
		if(!CollectionUtils.isEmpty(productCategories)) {
			productCategories.stream().forEach(productCategory -> listOfProductCategoryDto.add(productCategoryToProductCategoryDto(productCategory)));
		}
		productCategoryDtos.setProductCategories(listOfProductCategoryDto);
		return productCategoryDtos;
	}

	
	public ProductCategoryDto productCategoryToProductCategoryDto(ProductCategory productCategory) {
		if(null == productCategory) {
			return null;
		}
		
		ProductCategoryDto productCategoryDto = new ProductCategoryDto();
		productCategoryDto.setId(productCategory.getId());
		productCategoryDto.setName(productCategory.getName());
		productCategoryDto.setCreatedBy(productCategory.getCreatedBy());
		productCategoryDto.setCreatedTs(productCategory.getCreatedTs());
		productCategoryDto.setLastModifiedBy(productCategory.getLastModifiedBy());
		productCategoryDto.setLastModifiedTs(productCategory.getLastModifiedTs());
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
	
	public List<WhereClause> productCategorySearchRequestToWhereClauses(ProductCategorySearchRequest productCategorySearchRequest) {
		return getWhereClauses(productCategorySearchRequest.getId(), productCategorySearchRequest.getName());
	}
	
	public List<WhereClause> productCategoryDtoToWhereClauses(ProductCategoryDto productCategoryDto) {
		return getWhereClauses(productCategoryDto.getId(), productCategoryDto.getName());
	}
	
	private List<WhereClause> getWhereClauses(Long id, String name) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (id != null) {
			WhereClause whereClause = new WhereClause("id", String.valueOf(id), OperationType.EQUALS);
			whereClauses.add(whereClause);
		} else if (!StringUtils.isEmpty(name)) {
			WhereClause whereClause = new WhereClause("name", name, OperationType.LIKE);
			whereClauses.add(whereClause);
		}
		return whereClauses;
	}
}
