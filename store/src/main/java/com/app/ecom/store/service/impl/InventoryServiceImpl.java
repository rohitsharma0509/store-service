package com.app.ecom.store.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.ecom.store.client.InventoryServiceClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.OrderByClause;
import com.app.ecom.store.dto.inventory.InventoryDto;
import com.app.ecom.store.dto.inventory.InventoryDtos;
import com.app.ecom.store.dto.inventory.InventorySearchRequest;
import com.app.ecom.store.enums.SortOrder;
import com.app.ecom.store.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryServiceClient inventoryServiceClient;

	@Override
	public CustomPage<InventoryDto> searchInventory(Pageable pageable, Map<String, String> params) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		
		List<OrderByClause> orderByClauses = new ArrayList<>();
		OrderByClause orderByClause = new OrderByClause();
		orderByClause.setSortBy(FieldNames.CREATED_TS);
		orderByClause.setSortOrder(SortOrder.DESC);
		orderByClauses.add(orderByClause);
		
		InventorySearchRequest inventorySearchRequest = new InventorySearchRequest();
		inventorySearchRequest.setAgencyName(params.get(FieldNames.AGENCY_NAME));
		inventorySearchRequest.setPaymentStatus(params.get(FieldNames.PAYMENT_STATUS));
		inventorySearchRequest.setPaymentMode(params.get(FieldNames.PAYMENT_MODE));
		inventorySearchRequest.setOrderByClauses(orderByClauses);
		inventorySearchRequest.setOffset(offset);
		inventorySearchRequest.setLimit(limit);
		InventoryDtos inventoryDtos = inventoryServiceClient.searchInventories(inventorySearchRequest);
		Long totalRecords = inventoryServiceClient.countInventory(inventorySearchRequest);
		CustomPage<InventoryDto> page = new CustomPage<>();
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setContent(page != null ? inventoryDtos.getInventories() : null);
		page.setTotalRecords(totalRecords == null ? 0 : totalRecords.intValue());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

	@Override
	public InventoryDto getInventoryById(Long id) {
		return inventoryServiceClient.getInventoryById(id);
	}

	@Override
	public InventoryDto addInventory(@Valid InventoryDto inventoryDto) {
		return inventoryServiceClient.addUpdateInventory(inventoryDto);
	}

}
