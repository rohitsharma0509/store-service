package com.app.ecom.store.masterdata.dto.setting;

import com.app.ecom.store.masterdata.dto.DefaultSearchRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class SettingSearchRequest extends DefaultSearchRequest {
	
	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}