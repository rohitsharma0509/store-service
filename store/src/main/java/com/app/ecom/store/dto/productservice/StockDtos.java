package com.app.ecom.store.dto.productservice;

import java.util.List;

import com.app.ecom.store.dto.StockDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class StockDtos {
	@JsonProperty("stockDtos")
	private List<StockDto> stocks;

	public List<StockDto> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockDto> stocks) {
		this.stocks = stocks;
	}
}
