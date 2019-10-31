package com.app.ecom.store.token.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class TokenSearchRequest extends DefaultSearchRequest {
	@JsonProperty("addressId")
	private Long addressId;
}
