package com.app.ecom.store.orderservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class DefaultSearchRequest {
	
	@JsonProperty("orderByClauses")
	private List<OrderByClause> orderByClauses;

	@JsonProperty("offset")
	private Integer offset;

	@JsonProperty("limit")
	private Integer limit;

	public List<OrderByClause> getOrderByClauses() {
		return orderByClauses;
	}

	public void setOrderByClauses(List<OrderByClause> orderByClauses) {
		this.orderByClauses = orderByClauses;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
