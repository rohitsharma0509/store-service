package com.app.ecom.store.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.inventory.InventoryDto;
import com.app.ecom.store.dto.inventory.InventoryItemDto;
import com.app.ecom.store.enums.PaymentMode;
import com.app.ecom.store.enums.PaymentStatus;
import com.app.ecom.store.service.InventoryService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.validator.InventoryValidator;

@Controller
public class InventoryController {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private InventoryValidator inventoryValidator;
	
	@GetMapping(value =RequestUrls.INVENTORY)
	public String getAllInventories(Model model, @PageableDefault(page=1, size=10) Pageable pageable, 
			@RequestParam(required = false) String agencyName,
			@RequestParam(required = false) String paymentStatus,
			@RequestParam(required = false) String paymentMode) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.AGENCY_NAME, agencyName);
		params.put(FieldNames.PAYMENT_STATUS, paymentStatus);
		params.put(FieldNames.PAYMENT_MODE, paymentMode);
		CustomPage<InventoryDto> page = inventoryService.searchInventory(pageable, params);
		model.addAttribute(FieldNames.PAYMENT_STATUSES, PaymentStatus.values());
		model.addAttribute(FieldNames.PAYMENT_MODES, PaymentMode.values());
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.INVENTORY, page.getPageNumber()+1, page.getTotalPages(), params));
	    model.addAttribute(FieldNames.PAGE, page);
		return View.INVENTORIES;
	}
	
	@GetMapping(value = RequestUrls.ADD_INVENTORY)
	public String redirectToAddInventory(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		InventoryDto inventoryDto;
		if(id != null){
			inventoryDto = inventoryService.getInventoryById(id);
		}else {
			InventoryItemDto inventoryItemDto = new InventoryItemDto();
			inventoryDto = new InventoryDto();
			inventoryDto.setInventoryItems(Arrays.asList(inventoryItemDto));
		}
		model.addAttribute(FieldNames.INVENTORY_DTO, inventoryDto);
		model.addAttribute(FieldNames.PAYMENT_STATUSES, PaymentStatus.values());
		model.addAttribute(FieldNames.PAYMENT_MODES, PaymentMode.values());
		return View.ADD_INVENTORY;
	}
	
	@PostMapping(value = RequestUrls.INVENTORY)
	public String addInventory(Model model, @ModelAttribute(FieldNames.INVENTORY_DTO)  @Valid InventoryDto inventoryDto, BindingResult bindingResult) {
		inventoryValidator.validate(inventoryDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute(FieldNames.PAYMENT_STATUSES, PaymentStatus.values());
			model.addAttribute(FieldNames.PAYMENT_MODES, PaymentMode.values());
			return View.ADD_INVENTORY;
		}
		
		inventoryService.addInventory(inventoryDto);
		return "redirect:"+RequestUrls.INVENTORY;
	}
	
	@GetMapping(value = RequestUrls.VIEW_INVENTORY)
	public String viewProduct(Model model, @RequestParam(value = FieldNames.ID, required=true) Long id) {
		InventoryDto inventoryDto = inventoryService.getInventoryById(id);
		model.addAttribute(FieldNames.INVENTORY_DTO, inventoryDto);
		return View.VIEW_INVENTORY;
	}
}
