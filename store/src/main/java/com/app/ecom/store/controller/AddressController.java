package com.app.ecom.store.controller;

import javax.validation.Valid;

import com.app.ecom.store.dto.addresslookupservice.AddressDto;
import com.app.ecom.store.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping(value = "/manageAddress")
	public String manageAddress(Model model) {
		model.addAttribute("addressDto", new AddressDto());
		return "addAddress";
	}
	
	@PostMapping(value = "/addAddress")
	@ResponseBody
	public void addAddress(Model model, @ModelAttribute  @Valid AddressDto addressDto, BindingResult bindingResult) {
		addressService.addAddress(addressDto);
	}
}
