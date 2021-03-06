package com.app.ecom.store.userservice.dto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import com.app.ecom.store.userservice.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivilegeDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("oldName")
	private String oldName;
	
	@JsonProperty("type")
	private RoleType type;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("parentId")
	private Long parentId;
	
	@JsonProperty("isInRole")
	private Boolean isInRole;
	
	@JsonProperty("childPrivileges")
	private Set<PrivilegeDto> childPrivileges;
	
	@JsonProperty("roleDtos")
	private List<RoleDto> roleDtos;
	
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

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsInRole() {
		return isInRole;
	}

	public void setIsInRole(Boolean isInRole) {
		this.isInRole = isInRole;
	}

	public Set<PrivilegeDto> getChildPrivileges() {
		return childPrivileges;
	}

	public void setChildPrivileges(Set<PrivilegeDto> childPrivileges) {
		this.childPrivileges = childPrivileges;
	}
	
	public List<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public void setRoleDtos(List<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
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
