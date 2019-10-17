package com.app.ecom.store.masterdata.dto.privilege;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class PrivilegeDtos {

	@JsonProperty("privileges")
	private List<PrivilegeDto> privileges;

	public List<PrivilegeDto> getPrivileges() {
		return privileges;
	}

	public void setProductCategories(List<PrivilegeDto> privileges) {
		this.privileges = privileges;
	}
}
