package com.app.ecom.store.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InventoryItemDto {
	private Long id;
	private String name;
	private String code;
	private Double mrp;
	private Integer quantity;
	private Integer freeQuantity;
	private Double rate;
	private Double amount;
	private Double schemeDiscount;
	private Double tradeDiscount;
	private Double otherDiscount;
	private Double taxableAmount;
	private Double cgst;
	private Double sgst;
	private Double igst;
	private Double totalAmount;
	private boolean isDeleted;
}
