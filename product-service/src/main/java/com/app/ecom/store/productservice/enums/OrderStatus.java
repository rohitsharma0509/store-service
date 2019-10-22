package com.app.ecom.store.productservice.enums;

public enum OrderStatus {
	ORDERED("ordered"),
	CANCELLED("cancelled");
	
	private String status;
	
	private OrderStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}