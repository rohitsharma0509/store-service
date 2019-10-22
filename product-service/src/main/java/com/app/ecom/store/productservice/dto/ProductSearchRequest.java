package com.app.ecom.store.productservice.dto;

import java.util.List;

import com.app.ecom.store.productservice.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class ProductSearchRequest extends DefaultSearchRequest {
	@JsonProperty("productIds")
	private List<Long> productIds;
	
	@JsonProperty("categoryIds")
	private List<Long> categoryIds;
	
	@JsonProperty("brandName")
	private String brandName;
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("status")
	private ProductStatus status;
	
	@JsonProperty("isExcel")
	private Boolean isExcel;

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public Boolean getIsExcel() {
		return isExcel;
	}

	public void setIsExcel(Boolean isExcel) {
		this.isExcel = isExcel;
	}
}
