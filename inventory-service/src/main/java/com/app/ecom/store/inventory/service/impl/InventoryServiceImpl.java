package com.app.ecom.store.inventory.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.app.ecom.store.inventory.dto.InventoryDto;
import com.app.ecom.store.inventory.dto.InventoryDtos;
import com.app.ecom.store.inventory.dto.InventoryItemDto;
import com.app.ecom.store.inventory.dto.InventorySearchRequest;
import com.app.ecom.store.inventory.dto.QueryRequest;
import com.app.ecom.store.inventory.handler.QueryHandler;
import com.app.ecom.store.inventory.mapper.InventoryMapper;
import com.app.ecom.store.inventory.model.Inventory;
import com.app.ecom.store.inventory.repository.InventoryItemRepository;
import com.app.ecom.store.inventory.repository.InventoryRepository;
import com.app.ecom.store.inventory.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	
	@Autowired
	private InventoryMapper inventoryMapper;
	
	@Autowired
	private QueryHandler<Inventory> queryHandler;
	
	@Override
	@Transactional
	public InventoryDto addInventory(InventoryDto inventoryDto) {
		List<InventoryItemDto> inventoryItems = inventoryDto.getInventoryItems();
		if(!CollectionUtils.isEmpty(inventoryItems)) {
			List<Long> itemIdsToDelete = inventoryItems.stream().filter(InventoryItemDto::isDeleted)
					.map(InventoryItemDto::getId).collect(Collectors.toList());
			log.info("Total Inventory items before delete: {}, Number of items mark for deleted: {}", inventoryItems.size(), itemIdsToDelete.size());
			inventoryItemRepository.deleteByIdIn(itemIdsToDelete);
			
			inventoryItems.removeIf(item -> itemIdsToDelete.contains(item.getId()));
			log.info("Total Inventory items after delete: {}", inventoryItems.size());
			inventoryDto.setInventoryItems(inventoryItems);
		}
		return inventoryMapper.toInventoryDto(inventoryRepository.save(inventoryMapper.toInventory(inventoryDto)));
	}

	@Override
	public InventoryDtos searchInventories(InventorySearchRequest inventorySearchRequest) {
		queryHandler.setType(Inventory.class);
		QueryRequest queryRequest = QueryRequest.builder()
				.whereClauses(inventoryMapper.toWhereClauses(inventorySearchRequest))
				.orderByClauses(inventorySearchRequest.getOrderByClauses())
				.offset(inventorySearchRequest.getOffset())
				.limit(inventorySearchRequest.getLimit()).build();
		List<Inventory> inventories = queryHandler.findByQueryRequest(queryRequest);
		return inventoryMapper.toInventoryDtos(inventories);
	}
	
	@Override
	public InventoryDto getInventoryById(Long id) {
		Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
		return optionalInventory.isPresent() ? inventoryMapper.toInventoryDto(optionalInventory.get()) : null;
	}

	@Override
	public Long countInventory(InventorySearchRequest inventorySearchRequest) {
		queryHandler.setType(Inventory.class);
		QueryRequest queryRequest = QueryRequest.builder()
				.whereClauses(inventoryMapper.toWhereClauses(inventorySearchRequest)).build();
		return queryHandler.countByQueryRequest(queryRequest, "id");
	}
}
