package com.app.ecom.store.address.service.impl;

import java.util.List;

import com.app.ecom.store.address.dto.AddressDto;
import com.app.ecom.store.address.dto.AddressDtos;
import com.app.ecom.store.address.dto.AddressSearchRequest;
import com.app.ecom.store.address.dto.IdsDto;
import com.app.ecom.store.address.dto.QueryRequest;
import com.app.ecom.store.address.handler.QueryHandler;
import com.app.ecom.store.address.mapper.AddressMapper;
import com.app.ecom.store.address.model.Address;
import com.app.ecom.store.address.repository.AddressRepository;
import com.app.ecom.store.address.service.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class AddressServiceImpl implements AddressService {
	
	private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private QueryHandler<Address> queryHandler;

	@Override
	public AddressDto createUpdateAddress(AddressDto addressDto) {
		return addressMapper.addressToAddressDto(addressRepository.save(addressMapper.addressDtoToAddress(addressDto)));
	}

	@Override
	public AddressDtos getAddresses(AddressSearchRequest addressSearchRequest) {
		queryHandler.setType(Address.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(addressMapper.addressSearchRequestToWhereClauses(addressSearchRequest));
		queryRequest.setOrderByClauses(addressSearchRequest.getOrderByClauses());
		queryRequest.setOffset(addressSearchRequest.getOffset());
		queryRequest.setLimit(addressSearchRequest.getLimit());
		List<Address> addresses = queryHandler.findByQueryRequest(queryRequest);
		return addressMapper.addressesToAddressDtos(addresses);
	}

	@Override
	public Long countAddresses(AddressSearchRequest addressSearchRequest) {
			queryHandler.setType(Address.class);
			QueryRequest queryRequest = new QueryRequest();
			queryRequest.setWhereClauses(addressMapper.addressSearchRequestToWhereClauses(addressSearchRequest));
			return queryHandler.countByQueryRequest(queryRequest, "id");
	}

	@Override
	public void deleteAddresses(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			addressRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			addressRepository.deleteById(idsDto.getIds().get(0));
		} else {
			addressRepository.deleteByIdIn(idsDto.getIds());
		}
	}
}
