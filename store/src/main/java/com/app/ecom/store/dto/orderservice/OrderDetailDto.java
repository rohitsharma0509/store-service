package com.app.ecom.store.dto.orderservice;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class OrderDetailDto {
	@JsonProperty("orderDetailId")
	private Long id;

	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("productId")
	private Long productId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("perProductPrice")
	private Double perProductPrice;
	
	@JsonProperty("availableQuantity")
	private Integer availableQuantity;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPerProductPrice() {
		return perProductPrice;
	}

	public void setPerProductPrice(Double perProductPrice) {
		this.perProductPrice = perProductPrice;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
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
