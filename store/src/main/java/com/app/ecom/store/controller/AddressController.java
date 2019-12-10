package com.app.ecom.store.controller;

import javax.validation.Valid;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.addresslookupservice.AddressDto;
import com.app.ecom.store.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping(value = RequestUrls.MANAGE_ADDRESS)
	public String manageAddress(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		AddressDto addressDto;
		if (id != null) {
			addressDto = addressService.getAddressById(id);
		} else {
			addressDto = new AddressDto();
		}
		model.addAttribute(FieldNames.ADDRESS_DTO, addressDto);
		return View.ADD_ADDRESS;
	}
	
	@PostMapping(value = RequestUrls.ADD_ADDRESS)
	@ResponseBody
	public void addAddress(Model model, @ModelAttribute  @Valid AddressDto addressDto, BindingResult bindingResult) {
		addressService.addAddress(addressDto);
	}
}
