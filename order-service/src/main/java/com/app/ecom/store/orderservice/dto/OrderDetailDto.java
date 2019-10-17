package com.app.ecom.store.orderservice.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderDetailDto {
	@JsonProperty("orderDetailId")
	private Long id;

	@JsonProperty("orderDetailId")
	private Integer quantity;

	@JsonProperty("productId")
	private Long productId;
	
	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("createdTs")
	private ZonedDateTime createdTs;

	@JsonProperty("lastModifiedBy")
	private String lastModifiedBy;

	@JsonProperty("lastModifiedTs")
	private ZonedDateTime lastModifiedTs;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public ZonedDateTime getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(ZonedDateTime createdTs) {
		this.createdTs = createdTs;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public ZonedDateTime getLastModifiedTs() {
		return lastModifiedTs;
	}

	public void setLastModifiedTs(ZonedDateTime lastModifiedTs) {
		this.lastModifiedTs = lastModifiedTs;
	}
}