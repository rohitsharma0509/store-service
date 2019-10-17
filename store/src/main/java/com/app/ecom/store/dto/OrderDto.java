package com.app.ecom.store.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class OrderDto {
	
	@JsonProperty("id")
	private Long id;

	@JsonProperty("orderNumber")
	private String orderNumber;

	@JsonProperty("totalAmount")
	private Double totalAmount;

	@JsonProperty("orderDate")
	private String orderDate;
	
	@JsonProperty("status")
	private String status;

	@JsonProperty("userDto")
	private UserDto userDto;

	@JsonProperty("productDtos")
	private Set<ProductDto> productDtos = new HashSet<>();
	
	@JsonProperty("addressDto")
	private AddressDto addressDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public Set<ProductDto> getProductDtos() {
		return productDtos;
	}

	public void setProductDtos(Set<ProductDto> productDtos) {
		this.productDtos = productDtos;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

}
