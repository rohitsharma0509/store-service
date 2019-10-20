package com.app.ecom.store.dto.orderservice;


import java.time.ZonedDateTime;
import java.util.List;

import com.app.ecom.store.dto.DefaultSearchRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(value = Include.NON_NULL)
public class OrderSearchRequest extends DefaultSearchRequest {

	@JsonProperty("orderId")
	private Long orderId;
	
	@JsonProperty("orderNumber")
	private String orderNumber;

	@JsonProperty("fromDate")
	private ZonedDateTime fromDate;
	
	@JsonProperty("toDate")
	private ZonedDateTime toDate;
	
	@JsonProperty("userId")
	private Long userId;
	
	@JsonProperty("productIds")
	private List<Long> productIds;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public ZonedDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(ZonedDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public ZonedDateTime getToDate() {
		return toDate;
	}

	public void setToDate(ZonedDateTime toDate) {
		this.toDate = toDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
}