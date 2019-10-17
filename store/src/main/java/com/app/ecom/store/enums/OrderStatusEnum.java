package com.app.ecom.store.enums;

public enum OrderStatusEnum {
	ORDERED("ordered"),
	CANCELLED("cancelled");
	
	private String status;
	
	private OrderStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
