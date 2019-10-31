package com.app.ecom.store.dto.addresslookupservice;

import com.app.ecom.store.dto.DefaultSearchRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class AddressSearchRequest extends DefaultSearchRequest {
	@JsonProperty("addressId")
	private Long addressId;
	
	@JsonProperty("userId")
	private Long userId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}