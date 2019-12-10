package com.app.ecom.store.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.client.AddressLookupServiceClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.addresslookupservice.AddressDto;
import com.app.ecom.store.dto.addresslookupservice.AddressDtos;
import com.app.ecom.store.dto.addresslookupservice.AddressSearchRequest;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private AddressLookupServiceClient addressLookupServiceClient;

	@Override
	public List<AddressDto> getAddressByUserId(Long userId) {
		AddressSearchRequest addressSearchRequest = new AddressSearchRequest();
		addressSearchRequest.setUserId(userId);
		AddressDtos addressDtos = addressLookupServiceClient.getAddresses(addressSearchRequest);
		return addressDtos == null ? null : addressDtos.getAddresses();
	}

	@Override
	public void addAddress(AddressDto addressDto) {
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		addressDto.setUserId(userDto.getId());
		addressLookupServiceClient.createUpdateAddress(addressDto);
	}
	
	@Override 
	public AddressDto getAddressById(Long addressId) {
		AddressSearchRequest addressSearchRequest = new AddressSearchRequest();
		addressSearchRequest.setAddressId(addressId);
		AddressDtos addressDtos = addressLookupServiceClient.getAddresses(addressSearchRequest);
		return addressDtos == null || CollectionUtils.isEmpty(addressDtos.getAddresses()) ? null : addressDtos.getAddresses().get(0);
	}
}
