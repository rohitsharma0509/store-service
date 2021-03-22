package com.app.ecom.store.inventory.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.app.ecom.store.inventory.dto.InventoryDto;
import com.app.ecom.store.inventory.dto.InventoryDtos;
import com.app.ecom.store.inventory.dto.InventoryItemDto;
import com.app.ecom.store.inventory.dto.InventorySearchRequest;
import com.app.ecom.store.inventory.dto.WhereClause;
import com.app.ecom.store.inventory.enums.OperationType;
import com.app.ecom.store.inventory.model.Inventory;
import com.app.ecom.store.inventory.model.InventoryItem;

@Component
public class InventoryMapper {
	
	public InventoryDtos toInventoryDtos(List<Inventory> inventories) {
		List<InventoryDto> listOfInventoryDto = new ArrayList<>();
		if(!CollectionUtils.isEmpty(inventories)) {
			inventories.stream().forEach(inventory -> listOfInventoryDto.add(toInventoryDto(inventory)));
		}
		return InventoryDtos.builder().inventories(listOfInventoryDto).build();
	}

	public InventoryDto toInventoryDto(Inventory inventory) {
		if(Objects.isNull(inventory)) {
			return null;
		}
		
		return InventoryDto.builder()
				.id(inventory.getId()).billNumber(inventory.getBillNumber())
				.billDate(inventory.getBillDate()).gstNumber(inventory.getGstNumber())
				.agencyName(inventory.getAgencyName()).agencyAddress(inventory.getAgencyAddress())
				.agencyEmail(inventory.getAgencyEmail()).agencyContactNumber(inventory.getAgencyContactNumber())
				.panNumber(inventory.getPanNumber()).paymentStatus(inventory.getPaymentStatus()).paymentMode(inventory.getPaymentMode())
				.salesmanName(inventory.getSalesmanName()).totalAmount(inventory.getTotalAmount())
				.inventoryItems(toInventoryItemDtos(inventory.getInventoryItems()))
				.build();
	}
	
	public List<InventoryItemDto> toInventoryItemDtos(List<InventoryItem> inventoryItems) {
		if(CollectionUtils.isEmpty(inventoryItems)) {
			return Collections.emptyList();
		}
		
		List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();
		inventoryItems.stream().forEach(inventoryItem -> inventoryItemDtos.add(toInventoryItemDto(inventoryItem)));
		return inventoryItemDtos;
	}
	
	public InventoryItemDto toInventoryItemDto(InventoryItem inventoryItem) {
		if(Objects.isNull(inventoryItem)) {
			return null;
		}
		
		return InventoryItemDto.builder()
				.id(inventoryItem.getId()).name(inventoryItem.getName()).code(inventoryItem.getCode())
				.mrp(inventoryItem.getMrp()).quantity(inventoryItem.getQuantity()).freeQuantity(inventoryItem.getFreeQuantity())
				.rate(inventoryItem.getRate()).amount(inventoryItem.getAmount()).schemeDiscount(inventoryItem.getSchemeDiscount())
				.tradeDiscount(inventoryItem.getTradeDiscount()).otherDiscount(inventoryItem.getOtherDiscount())
				.taxableAmount(inventoryItem.getTaxableAmount()).cgst(inventoryItem.getCgst()).sgst(inventoryItem.getSgst())
				.igst(inventoryItem.getIgst()).totalAmount(inventoryItem.getTotalAmount())    
				.build();
	}

	public Inventory toInventory(InventoryDto inventoryDto) {
		if(null == inventoryDto) {
			return null;
		}
		
		Inventory inventory = Inventory.builder()
				.id(inventoryDto.getId()).billNumber(inventoryDto.getBillNumber())
				.billDate(inventoryDto.getBillDate()).gstNumber(inventoryDto.getGstNumber())
				.agencyName(inventoryDto.getAgencyName()).agencyAddress(inventoryDto.getAgencyAddress())
				.agencyEmail(inventoryDto.getAgencyEmail()).agencyContactNumber(inventoryDto.getAgencyContactNumber())
				.panNumber(inventoryDto.getPanNumber()).paymentStatus(inventoryDto.getPaymentStatus()).paymentMode(inventoryDto.getPaymentMode())
				.salesmanName(inventoryDto.getSalesmanName()).totalAmount(inventoryDto.getTotalAmount())
				.build();
		inventory.setInventoryItems(toInventoryItems(inventoryDto.getInventoryItems(), inventory));
		return inventory;
	}
	
	public List<InventoryItem> toInventoryItems(List<InventoryItemDto> inventoryItemDtos, Inventory inventory) {
		if(CollectionUtils.isEmpty(inventoryItemDtos)) {
			return Collections.emptyList();
		}
		
		double totalAmount = inventoryItemDtos.stream().filter(Objects::nonNull).mapToDouble(InventoryItemDto::getTotalAmount).sum();
		inventory.setTotalAmount(totalAmount);
		List<InventoryItem> inventoryItems = new ArrayList<>();
		inventoryItemDtos.stream().forEach(inventoryItemDto -> inventoryItems.add(toInventoryItem(inventoryItemDto, inventory)));
		return inventoryItems;
	}
	
	public InventoryItem toInventoryItem(InventoryItemDto inventoryItemDto, Inventory inventory) {
		if(Objects.isNull(inventoryItemDto)) {
			return null;
		}
		
		return InventoryItem.builder()
				.id(inventoryItemDto.getId()).name(inventoryItemDto.getName()).code(inventoryItemDto.getCode())
				.mrp(inventoryItemDto.getMrp()).quantity(inventoryItemDto.getQuantity()).freeQuantity(inventoryItemDto.getFreeQuantity())
				.rate(inventoryItemDto.getRate()).amount(inventoryItemDto.getAmount()).schemeDiscount(inventoryItemDto.getSchemeDiscount())
				.tradeDiscount(inventoryItemDto.getTradeDiscount()).otherDiscount(inventoryItemDto.getOtherDiscount())
				.taxableAmount(inventoryItemDto.getTaxableAmount()).cgst(inventoryItemDto.getCgst()).sgst(inventoryItemDto.getSgst())
				.igst(inventoryItemDto.getIgst()).totalAmount(inventoryItemDto.getTotalAmount())
				.inventory(inventory)
				.build();
	}

	public List<WhereClause> toWhereClauses(InventorySearchRequest inventorySearchRequest) {
		return getWhereClauses(inventorySearchRequest.getAgencyName(), inventorySearchRequest.getPaymentStatus(), inventorySearchRequest.getPaymentMode());
	}

	private List<WhereClause> getWhereClauses(String agencyName, String paymentStatus, String paymentMode) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (!StringUtils.isEmpty(agencyName)) {
			WhereClause whereClause = WhereClause.builder().key("agencyName")
					.value(agencyName).operation(OperationType.LIKE).build();
			whereClauses.add(whereClause);
		}
		if (!StringUtils.isEmpty(paymentStatus)) {
			WhereClause whereClause = WhereClause.builder().key("paymentStatus")
					.value(paymentStatus).operation(OperationType.EQUALS).build();
			whereClauses.add(whereClause);
		}
		if (!StringUtils.isEmpty(paymentMode)) {
			WhereClause whereClause = WhereClause.builder().key("paymentMode")
					.value(paymentMode).operation(OperationType.EQUALS).build();
			whereClauses.add(whereClause);
		}
		return whereClauses;
	}
}
