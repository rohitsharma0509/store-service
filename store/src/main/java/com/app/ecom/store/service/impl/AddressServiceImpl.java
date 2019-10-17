package com.app.ecom.store.service.impl;

import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.dto.AddressDto;
import com.app.ecom.store.mapper.AddressMapper;
import com.app.ecom.store.model.Address;
import com.app.ecom.store.model.User;
import com.app.ecom.store.repository.AddressRepository;
import com.app.ecom.store.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public Set<AddressDto> getAddressByUser(User user) {
		return addressMapper.addressesToAddressDtos(addressRepository.findByUser(user));
	}

	@Override
	public void addAddress(AddressDto addressDto) {
		Address address = addressMapper.addressDtoToAddress(addressDto);
		address.setUser((User) httpSession.getAttribute("user"));
		addressRepository.save(address);
	}
	
	@Override 
	public Address getAddressById(Long addressId) {
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		if(optionalAddress.isPresent()) {
			return optionalAddress.get();
		} else {
			return null;
		}
	}
}
