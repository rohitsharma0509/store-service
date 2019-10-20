package com.app.ecom.store.dto.orderservice;

import java.util.List;

import com.app.ecom.store.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class OrderDtos {

	@JsonProperty("orderDtos")
	private List<OrderDto> orders;

	public List<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}
}
