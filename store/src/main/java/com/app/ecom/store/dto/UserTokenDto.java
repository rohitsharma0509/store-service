package com.app.ecom.store.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class UserTokenDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("token")
	private String token;
	
	@JsonProperty("userDto")
	private UserDto userDto;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserTokenDto [");
		builder.append("id=").append(id);
		builder.append(", token=").append(token);
		builder.append(", userDto=").append(userDto);
		builder.append(", expiryDate=").append(expiryDate);
		builder.append("]");
		return builder.toString();
	}
}
