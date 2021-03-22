package com.app.ecom.store.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.app.ecom.store.inventory.enums.OperationType;

@Setter
@Getter
@Builder
public class WhereClause {	
	private String key;
	private Object value;
	private OperationType operation;
}
