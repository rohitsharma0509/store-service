package com.app.ecom.store.masterdata.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "product_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	@Column(name = "category_name")
	private String name;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts", columnDefinition = "timestamp")
	private ZonedDateTime createdTs;

	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	@Column(name = "last_modified_ts", columnDefinition = "timestamp")
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
