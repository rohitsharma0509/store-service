package com.app.ecom.store.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.dto.orderservice.OrderDetailDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class ShoppingCart implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("orderDetailDtos")
	private List<OrderDetailDto> orderDetailDtos = new ArrayList<>();

	@JsonProperty("totalPrice")
	private Double totalPrice = 0.0;

	public List<OrderDetailDto> getOrderDetailDtos() {
		return orderDetailDtos;
	}

	public void setOrderDetailDtos(List<OrderDetailDto> orderDetailDtos) {
		this.orderDetailDtos = orderDetailDtos;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
