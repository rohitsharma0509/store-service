package com.app.ecom.store.userservice.service;

import com.app.ecom.store.userservice.dto.AddressDto;
import com.app.ecom.store.userservice.dto.AddressDtos;
import com.app.ecom.store.userservice.dto.AddressSearchRequest;
import com.app.ecom.store.userservice.dto.IdsDto;

public interface AddressService {

	AddressDto createUpdateAddress(AddressDto addressDto);

	AddressDtos getAddresses(AddressSearchRequest addressSearchRequest);

	Long countAddresses(AddressSearchRequest addressSearchRequest);

	void deleteAddresses(IdsDto idsDto);
}
