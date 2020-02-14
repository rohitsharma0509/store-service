package com.app.ecom.store.dto.supportservice;

import java.time.ZonedDateTime;
import java.util.List;

import com.app.ecom.store.enums.Priority;
import com.app.ecom.store.enums.SupportTicketStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class SupportTicketDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("number")
	private String number;
	
	@JsonProperty("orderNumber")
	private String orderNumber;
	
	@JsonProperty("priority")
	private Priority priority;
	
	@JsonProperty("status")
	private SupportTicketStatus status;
	
	@JsonProperty("assignedTo")
	private String assignedTo;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("statusChangeHistoryDtos")
	private List<SupportTicketStatusChangeHistoryDto> supportTicketStatusChangeHistoryDtos;
	
	@JsonProperty("activityHistoryDtos")
	private List<SupportTicketActivityHistoryDto> supportTicketActivityHistoryDtos;
	
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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public SupportTicketStatus getStatus() {
		return status;
	}

	public void setStatus(SupportTicketStatus status) {
		this.status = status;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SupportTicketStatusChangeHistoryDto> getSupportTicketStatusChangeHistoryDtos() {
		return supportTicketStatusChangeHistoryDtos;
	}

	public void setSupportTicketStatusChangeHistoryDtos(
			List<SupportTicketStatusChangeHistoryDto> supportTicketStatusChangeHistoryDtos) {
		this.supportTicketStatusChangeHistoryDtos = supportTicketStatusChangeHistoryDtos;
	}

	public List<SupportTicketActivityHistoryDto> getSupportTicketActivityHistoryDtos() {
		return supportTicketActivityHistoryDtos;
	}

	public void setSupportTicketActivityHistoryDtos(
			List<SupportTicketActivityHistoryDto> supportTicketActivityHistoryDtos) {
		this.supportTicketActivityHistoryDtos = supportTicketActivityHistoryDtos;
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
