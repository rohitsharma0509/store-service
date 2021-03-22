package com.app.ecom.store.userservice.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecom.store.userservice.constants.Endpoint;
import com.app.ecom.store.userservice.dto.AddressDto;
import com.app.ecom.store.userservice.dto.AddressDtos;
import com.app.ecom.store.userservice.dto.AddressSearchRequest;
import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.service.AddressService;
import com.app.ecom.store.userservice.util.CommonUtil;

@RestController
public class AddressResource {
	
	private static final Logger logger = LogManager.getLogger(AddressResource.class);
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.ADDRESS)
	public ResponseEntity<AddressDto> createUpdateAddress(@RequestBody AddressDto addressDto) {
		try {
			AddressDto createdAddressDto = addressService.createUpdateAddress(addressDto);
			return new ResponseEntity<>(createdAddressDto, addressDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while adding address: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.ADDRESS)
	public ResponseEntity<AddressDtos> getAddresses(@RequestBody AddressSearchRequest addressSearchRequest) {
		try {
			AddressDtos addressDtos = addressService.getAddresses(addressSearchRequest);
			return new ResponseEntity<>(addressDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting address: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_ADDRESS)
	public ResponseEntity<Long> countAddresses(@RequestBody AddressSearchRequest addressSearchRequest) {
		try {
			Long noOfAddresses = addressService.countAddresses(addressSearchRequest);
			return new ResponseEntity<>(noOfAddresses, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting address: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.ADDRESS)
	public ResponseEntity<Void> deleteAddresss(@RequestBody IdsDto idsDto) {
		try {
			addressService.deleteAddresses(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting address: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}