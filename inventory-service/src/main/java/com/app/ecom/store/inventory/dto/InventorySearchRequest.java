package com.app.ecom.store.inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(value = Include.NON_NULL)
public class InventorySearchRequest extends DefaultSearchRequest {
	
	@JsonProperty("agencyName")
	private String agencyName;
	
	@JsonProperty("paymentStatus")
	private String paymentStatus;
	
	@JsonProperty("paymentMode")
	private String paymentMode;
	
	@JsonProperty("isExcel")
	private Boolean isExcel;
}
