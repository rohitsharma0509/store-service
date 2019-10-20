package com.app.ecom.store.service;

import java.util.Set;

import com.app.ecom.store.dto.AddressDto;
import com.app.ecom.store.model.User;

public interface AddressService {
	Set<AddressDto> getAddressByUser(User user);
	
	void addAddress(AddressDto addressDto);
	
	AddressDto getAddressById(Long addressId);

}
