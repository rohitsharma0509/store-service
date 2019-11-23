package com.app.ecom.store.productservice.enums;

public enum FileType {
	XML("xml"),
	CSV("csv");
	
	private FileType(String type) {
		this.type = type;
	}
	
	private String type;

	public String getType() {
		return type;
	}	
}
