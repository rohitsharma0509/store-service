package com.app.ecom.store.inventory.controller;

import com.app.ecom.store.inventory.constants.Endpoint;
import com.app.ecom.store.inventory.dto.InventoryDto;
import com.app.ecom.store.inventory.dto.InventoryDtos;
import com.app.ecom.store.inventory.dto.InventorySearchRequest;
import com.app.ecom.store.inventory.service.InventoryService;
import com.app.ecom.store.inventory.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class InventoryController {

	private static final Logger logger = LogManager.getLogger(InventoryController.class);

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private CommonUtil commonUtil;

	@PutMapping(value = Endpoint.INVENTORY)
	public ResponseEntity<InventoryDto> addInventory(@RequestBody InventoryDto inventoryDto) {
		try {
			InventoryDto createdInventoryDto = inventoryService.addInventory(inventoryDto);
			return new ResponseEntity<>(createdInventoryDto,
					inventoryDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while add/upadte Inventory : ")
					.append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = Endpoint.INVENTORY + "/{id}")
	public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long id) {
		try {
			InventoryDto inventoryDto = inventoryService.getInventoryById(id);
			return new ResponseEntity<>(inventoryDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting Inventory: ")
					.append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.INVENTORY)
	public ResponseEntity<InventoryDtos> searchInventories(@RequestBody InventorySearchRequest inventorySearchRequest) {
		try {
			InventoryDtos inventoryDtos = inventoryService.searchInventories(inventorySearchRequest);
			return new ResponseEntity<>(inventoryDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting Inventory : ")
					.append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.COUNT_INVENTORY)
	public ResponseEntity<Long> countInventory(@RequestBody InventorySearchRequest inventorySearchRequest) {
		try {
			Long noOfInventories = inventoryService.countInventory(inventorySearchRequest);
			return new ResponseEntity<>(noOfInventories, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting Inventory : ")
					.append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}