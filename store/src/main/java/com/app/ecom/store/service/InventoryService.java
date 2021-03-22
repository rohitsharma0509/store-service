package com.app.ecom.store.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.inventory.InventoryDto;

public interface InventoryService {
	CustomPage<InventoryDto> searchInventory(Pageable pageable, Map<String, String> params);

	InventoryDto getInventoryById(Long id);

	InventoryDto addInventory(@Valid InventoryDto inventoryDto);
}
