package com.app.ecom.store.dto.helpservice;

import java.time.ZonedDateTime;

import com.app.ecom.store.dto.DefaultSearchRequest;
import com.app.ecom.store.enums.SupportTicketStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class SupportTicketSearchRequest extends DefaultSearchRequest {
	
	@JsonProperty("ticketId")
	private Long ticketId;
	
	@JsonProperty("ticketNumber")
	private String ticketNumber;
	
	@JsonProperty("orderNumber")
	private String orderNumber;
	
	@JsonProperty("assignedTo")
	private String assignedTo;
	
	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("createdTs")
	private ZonedDateTime createdTs;
	
	@JsonProperty("status")
	private SupportTicketStatus status;
	
	@JsonProperty("isExcel")
	private Boolean isExcel;
	
	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public SupportTicketStatus getStatus() {
		return status;
	}

	public void setStatus(SupportTicketStatus status) {
		this.status = status;
	}

	public Boolean getIsExcel() {
		return isExcel;
	}

	public void setIsExcel(Boolean isExcel) {
		this.isExcel = isExcel;
	}
}
