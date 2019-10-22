package com.app.ecom.store.userservice.dto;

import java.util.List;

import com.app.ecom.store.userservice.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class UserSearchRequest extends DefaultSearchRequest {
	@JsonProperty("userId")
	private List<Long> userId;
}
