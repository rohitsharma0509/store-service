package com.app.ecom.store.support.model;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "support_tickets")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SupportTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Long id;

	@Column(name = "ticket_number")
	private String number;
	
	@Column(name = "order_number")
	private String orderNumber;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "priority")
	private String priority;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "assigned_to")
	private String assignedTo;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts", columnDefinition = "timestamp")
	private ZonedDateTime createdTs;

	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	@Column(name = "last_modified_ts", columnDefinition = "timestamp")
	private ZonedDateTime lastModifiedTs;
	
	@OneToMany(mappedBy = "supportTicket", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<SupportTicketActivityHistory> supportTicketActivityHistories = new HashSet<>();
	
	@OneToMany(mappedBy = "supportTicket", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<SupportTicketStatusChangeHistory> supportTicketStatusChangeHistories = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
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

	public Set<SupportTicketActivityHistory> getSupportTicketActivityHistories() {
		return supportTicketActivityHistories;
	}

	public void setSupportTicketActivityHistories(Set<SupportTicketActivityHistory> supportTicketActivityHistories) {
		this.supportTicketActivityHistories = supportTicketActivityHistories;
	}

	public Set<SupportTicketStatusChangeHistory> getSupportTicketStatusChangeHistories() {
		return supportTicketStatusChangeHistories;
	}

	public void setSupportTicketStatusChangeHistories(Set<SupportTicketStatusChangeHistory> supportTicketStatusChangeHistories) {
		this.supportTicketStatusChangeHistories = supportTicketStatusChangeHistories;
	}


}
