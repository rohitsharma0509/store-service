package com.app.ecom.store.userservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivilegeSearchRequest extends DefaultSearchRequest {

	@JsonProperty("ids")
	private List<Long> ids;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}