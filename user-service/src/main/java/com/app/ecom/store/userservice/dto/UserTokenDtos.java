package com.app.ecom.store.userservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class UserTokenDtos {
	@JsonProperty("userTokenDtos")
	private List<UserTokenDto> userTokens;

	public List<UserTokenDto> getUserTokens() {
		return userTokens;
	}

	public void setUserTokens(List<UserTokenDto> userTokens) {
		this.userTokens = userTokens;
	}
}
