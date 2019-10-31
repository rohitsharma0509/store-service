package com.app.ecom.store.address.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.app.ecom.store.address.dto.AddressDto;
import com.app.ecom.store.address.dto.AddressDtos;
import com.app.ecom.store.address.dto.AddressSearchRequest;
import com.app.ecom.store.address.dto.WhereClause;
import com.app.ecom.store.address.enums.OperationType;
import com.app.ecom.store.address.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class AddressMapper {
	
	private static final Logger logger = LogManager.getLogger(AddressMapper.class);

	public List<Address> addressDtosToAddress(Set<AddressDto> addressDtos) {
		if(CollectionUtils.isEmpty(addressDtos)) {
			return Collections.emptyList();
		}
		
		List<Address> addresses = new ArrayList<>();
		addressDtos.stream().forEach(addressDto -> addresses.add(addressDtoToAddress(addressDto)));
		return addresses;
	}
	
	public Address addressDtoToAddress(AddressDto addressDto) {
		if(null == addressDto) {
			return null;
		}
		
		Address address = new Address();
		address.setId(addressDto.getId());
		address.setAddressLine1(addressDto.getAddressLine1());
		address.setAddressLine2(addressDto.getAddressLine2());
		address.setCity(addressDto.getCity());
		address.setPincode(addressDto.getPincode());
		address.setState(addressDto.getState());
		address.setCountry(addressDto.getCountry());
		address.setIsPrimary(addressDto.getIsPrimary());
		return address;
	}
	
	public AddressDtos addressesToAddressDtos(List<Address> addresses) {
		AddressDtos addressDtos = new AddressDtos();
		if(CollectionUtils.isEmpty(addresses)) {
			addressDtos.setAddresses(Collections.emptyList());
		} else {			
			List<AddressDto> listOfAddressDto = new ArrayList<>();
			addresses.stream().forEach(address -> listOfAddressDto.add(addressToAddressDto(address)));
			addressDtos.setAddresses(listOfAddressDto);
		}
		return addressDtos;
	}
	
	public AddressDto addressToAddressDto(Address address) {
		if(null == address) {
			return null;
		}
		
		AddressDto addressDto = new AddressDto();
		addressDto.setId(address.getId());
		addressDto.setAddressLine1(address.getAddressLine1());
		addressDto.setAddressLine2(address.getAddressLine2());
		addressDto.setCity(address.getCity());
		addressDto.setPincode(address.getPincode());
		addressDto.setState(address.getState());
		addressDto.setCountry(address.getCountry());
		addressDto.setIsPrimary(address.getIsPrimary());
		return addressDto;
	}

	public List<WhereClause> addressSearchRequestToWhereClauses(AddressSearchRequest addressSearchRequest) {
		return getWhereClauses(addressSearchRequest.getAddressId(), addressSearchRequest.getUserId());
	}

	private List<WhereClause> getWhereClauses(Long addressId, Long userId) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (addressId != null) {
			WhereClause whereClause = new WhereClause("id", String.valueOf(addressId), OperationType.EQUALS);
			whereClauses.add(whereClause);
		} else {
			if (userId != null) {
				WhereClause whereClause = new WhereClause("userId", String.valueOf(userId), OperationType.EQUALS);
				whereClauses.add(whereClause);
			}
		}
		return whereClauses;
	}
}
