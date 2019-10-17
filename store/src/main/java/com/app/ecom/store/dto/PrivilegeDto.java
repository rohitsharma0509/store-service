package com.app.ecom.store.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivilegeDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("oldName")
	private String oldName;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("parentId")
	private Long parentId;
	
	@JsonProperty("isInRole")
	private Boolean isInRole;
	
	@JsonProperty("childPrivileges")
	private Set<PrivilegeDto> childPrivileges;

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

	@Override
	public String toString() {
		return "PrivilegeDto [id=" + id + ", name=" + name + ", description=" + description + ", parentId=" + parentId
				+ ", isInRole=" + isInRole + ", childPrivileges=" + childPrivileges + "]";
	}	
}
