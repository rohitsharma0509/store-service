package com.app.ecom.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class Response {
	
	@JsonProperty("code")
	private Integer code;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("description")
	private String description;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorResponse [");
		builder.append("code=").append(code);
		builder.append(", message=").append(message);
		builder.append(", description=").append(description);
		builder.append("]");
		return builder.toString();
	}	
}
