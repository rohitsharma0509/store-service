package com.app.ecom.store.address.dto;

import com.app.ecom.store.address.enums.SortOrder;

public class OrderByClause {
	private String sortBy;
	private SortOrder sortOrder;
	
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public SortOrder getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
}
