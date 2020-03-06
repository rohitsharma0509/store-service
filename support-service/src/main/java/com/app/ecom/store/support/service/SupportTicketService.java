package com.app.ecom.store.support.service;

import com.app.ecom.store.support.dto.IdsDto;
import com.app.ecom.store.support.dto.SupportTicketDto;
import com.app.ecom.store.support.dto.SupportTicketDtos;
import com.app.ecom.store.support.dto.SupportTicketReportByStatus;
import com.app.ecom.store.support.dto.SupportTicketSearchRequest;

public interface SupportTicketService {
	
	SupportTicketDto createModifySupportTicket(SupportTicketDto supportTicketDto);
	
	SupportTicketDto getSupportTicketById(Long id);
	
	SupportTicketDtos getSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest);
	
	SupportTicketDtos getUnclosedSupportTickets(String username);
	
	Long countSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest);

	Long countUnclosedSupportTickets(String username);

	void deleteSupportTickets(IdsDto idsDto);
	
	void changeSupportTicketStatus(Long ticketId, String status);
	
	void changeSupportTicketPriority(Long ticketId, String priority);
	
	void changeSupportTicketStatusAndAssignedTo(Long ticketId, String status, String assignedTo);
	
	SupportTicketReportByStatus getSupportTicketStatusReport(String username);
	
}
