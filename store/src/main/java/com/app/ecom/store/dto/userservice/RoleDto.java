package com.app.ecom.store.dto.userservice;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import com.app.ecom.store.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class RoleDto {
	
	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("type")
	private RoleType type;
	
	@JsonProperty("oldName")
	private String oldName;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("isDeletable")
	private Boolean isDeletable;
	
	@JsonProperty("userDtos")
	private Set<UserDto> userDtos;
	
	@JsonProperty("privilegeDtos")
	private List<PrivilegeDto> privilegeDtos;
	
	@JsonProperty("createdBy")
	private String createdBy;
	
	@JsonProperty("createdTs")
	private ZonedDateTime createdTs;
	
	@JsonProperty("lastModifiedBy")
	private String lastModifiedBy;
	
	@JsonProperty("lastModifiedTs")
	private ZonedDateTime lastModifiedTs;

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
	
	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getIsDeletable() {
		return isDeletable;
	}

	public void setIsDeletable(Boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	public Set<UserDto> getUserDtos() {
		return userDtos;
	}

	public void setUserDtos(Set<UserDto> userDtos) {
		this.userDtos = userDtos;
	}

	public List<PrivilegeDto> getPrivilegeDtos() {
		return privilegeDtos;
	}

	public void setPrivilegeDtos(List<PrivilegeDto> privilegeDtos) {
		this.privilegeDtos = privilegeDtos;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public ZonedDateTime getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(ZonedDateTime createdTs) {
		this.createdTs = createdTs;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public ZonedDateTime getLastModifiedTs() {
		return lastModifiedTs;
	}

	public void setLastModifiedTs(ZonedDateTime lastModifiedTs) {
		this.lastModifiedTs = lastModifiedTs;
	}
}