package com.app.ecom.store.dto.masterdata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class ProductCategoryDtos {

	@JsonProperty("productCategories")
	private List<ProductCategoryDto> productCategories;

	public List<ProductCategoryDto> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategoryDto> productCategories) {
		this.productCategories = productCategories;
	}
}
