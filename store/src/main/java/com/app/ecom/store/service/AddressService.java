package com.app.ecom.store.service;

import java.util.List;

import com.app.ecom.store.dto.userservice.AddressDto;

public interface AddressService {
	List<AddressDto> getAddressByUserId(Long userId);
	
	void addAddress(AddressDto addressDto);
	
	AddressDto getAddressById(Long addressId);

}
