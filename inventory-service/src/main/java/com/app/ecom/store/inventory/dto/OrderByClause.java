package com.app.ecom.store.inventory.dto;

import com.app.ecom.store.inventory.enums.SortOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderByClause {
	private String sortBy;
	private SortOrder sortOrder;	
}
