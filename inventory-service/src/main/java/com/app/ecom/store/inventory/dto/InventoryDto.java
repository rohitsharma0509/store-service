package com.app.ecom.store.inventory.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InventoryDto {
	private Long id;
	private String billNumber;
	private String billDate;
	private String gstNumber;
	private String agencyName;
	private String agencyAddress;
	private String agencyEmail;
	private String agencyContactNumber;
	private String panNumber;
	private String paymentStatus;
	private String paymentMode;
	private String salesmanName;
	private Double totalAmount;
	private List<InventoryItemDto> inventoryItems;
}
