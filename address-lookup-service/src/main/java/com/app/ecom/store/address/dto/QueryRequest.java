package com.app.ecom.store.address.dto;

import java.util.List;

public class QueryRequest {
	private List<WhereClause> whereClauses;
	private List<OrderByClause> orderByClauses;
	private Integer offset;
	private Integer limit;

	public List<WhereClause> getWhereClauses() {
		return whereClauses;
	}

	public void setWhereClauses(List<WhereClause> whereClauses) {
		this.whereClauses = whereClauses;
	}

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
