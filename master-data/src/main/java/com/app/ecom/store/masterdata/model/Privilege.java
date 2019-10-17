/*package com.app.ecom.store.masterdata.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "privileges")
public class Privilege {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "privilege_id")
	private Long id;
	
	@Column(name = "privilege_name")
	private String name;
	
	@Column(name = "privilege_desc")
	private String description;
	
	@ManyToMany(mappedBy = "privileges")
	private Set<Role> roles;
	
	@Column(name = "parent_id")
	private Long parentId;

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}*/