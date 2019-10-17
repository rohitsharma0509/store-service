package com.app.ecom.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class ProfitLossDto {
	
	@JsonProperty("orderDate")
	private String orderDate;
	
	@JsonProperty("noOfOrders")
	private Integer noOfOrders;
	
	@JsonProperty("soldQuantity")
	private Integer soldQuantity;
	
	@JsonProperty("amountReceived")
	private Double amountReceived;
	
	@JsonProperty("profitOrLoss")
	private Double profitOrLoss;

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getNoOfOrders() {
		return noOfOrders;
	}

	public void setNoOfOrders(Integer noOfOrders) {
		this.noOfOrders = noOfOrders;
	}

	public Integer getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Integer soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Double getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(Double amountReceived) {
		this.amountReceived = amountReceived;
	}

	public Double getProfitOrLoss() {
		return profitOrLoss;
	}

	public void setProfitOrLoss(Double profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}
}
