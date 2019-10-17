package com.app.ecom.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class StockDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("categoryName")
	private String categoryName;
	
	@JsonProperty("brandName")
	private String brandName;

	@JsonProperty("totalQty")
	private int totalQty;
	
	@JsonProperty("availableQty")
	private int availableQty;
	
	@JsonProperty("orderedQty")
	private int orderedQty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public int getOrderedQty() {
		return orderedQty;
	}

	public void setOrderedQty(int orderedQty) {
		this.orderedQty = orderedQty;
	}

	@Override
	public String toString() {
		return "StockDto [id=" + id + ", code=" + code + ", name=" + name
				+ ", totalQty=" + totalQty + ", availableQty=" + availableQty
				+ ", orderedQty=" + orderedQty + "]";
	}
}
