package com.app.ecom.store.inventory.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InventoryDtos {
	@JsonProperty("inventoryDtos")
	private List<InventoryDto> inventories;
}
