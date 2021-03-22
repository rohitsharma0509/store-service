package com.app.ecom.store.inventory.service;

import com.app.ecom.store.inventory.dto.InventoryDto;
import com.app.ecom.store.inventory.dto.InventoryDtos;
import com.app.ecom.store.inventory.dto.InventorySearchRequest;
import org.springframework.web.multipart.MultipartFile;

public interface InventoryService {

	InventoryDto addInventory(InventoryDto inventoryDto);

	InventoryDtos searchInventories(InventorySearchRequest inventorySearchRequest);
	
	InventoryDto getInventoryById(Long id);

	Long countInventory(InventorySearchRequest inventorySearchRequest);
}
