package com.app.ecom.store.address.service;

import com.app.ecom.store.address.dto.AddressDto;
import com.app.ecom.store.address.dto.AddressDtos;
import com.app.ecom.store.address.dto.AddressSearchRequest;
import com.app.ecom.store.address.dto.IdsDto;

public interface AddressService {

	AddressDto createUpdateAddress(AddressDto addressDto);

	AddressDtos getAddresses(AddressSearchRequest addressSearchRequest);

	Long countAddresses(AddressSearchRequest addressSearchRequest);

	void deleteAddresses(IdsDto idsDto);
}
