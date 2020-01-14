package com.app.ecom.store.support.service;

import com.app.ecom.store.support.dto.IdsDto;
import com.app.ecom.store.support.dto.SupportTicketDto;
import com.app.ecom.store.support.dto.SupportTicketDtos;
import com.app.ecom.store.support.dto.SupportTicketSearchRequest;

public interface SupportTicketService {
	
	SupportTicketDto createModifySupportTicket(SupportTicketDto supportTicketDto);
	
	Long countSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest);

	SupportTicketDtos getSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest);
	
	void deleteSupportTickets(IdsDto idsDto);
}
