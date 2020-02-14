package com.app.ecom.store.dto.supportservice;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class SupportTicketDtos {

	@JsonProperty("supportTickets")
	private List<SupportTicketDto> supportTickets;

	public List<SupportTicketDto> getSupportTickets() {
		return supportTickets;
	}

	public void setSupportTickets(List<SupportTicketDto> supportTickets) {
		this.supportTickets = supportTickets;
	}
}