package com.app.ecom.store.masterdata.dto.privilege;

import com.app.ecom.store.masterdata.dto.DefaultSearchRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivilegeSearchRequest extends DefaultSearchRequest {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}