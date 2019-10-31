package com.app.ecom.store.token.dto;

import com.app.ecom.store.token.enums.OperationType;

public class WhereClause {
	
	private String key;
	private Object value;
	private OperationType operation;
	
	public WhereClause(String key, Object value, OperationType operation) {
		this.key = key;
		this.value = value;
		this.operation = operation;
	}
	
	public String getKey() {
		return key;
	}
	
	public Object getValue() {
		return value;
	}

	public OperationType getOperation() {
		return operation;
	}
}
