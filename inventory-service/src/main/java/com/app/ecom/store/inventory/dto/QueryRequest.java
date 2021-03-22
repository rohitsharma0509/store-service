package com.app.ecom.store.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QueryRequest {
	private List<WhereClause> whereClauses;
	private List<OrderByClause> orderByClauses;
	private Integer offset;
	private Integer limit;
}
