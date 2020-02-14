package com.app.ecom.store.service;

import java.util.Map;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.supportservice.SupportTicketAssignmentStrategyDto;
import com.app.ecom.store.dto.supportservice.SupportTicketDto;
import org.springframework.data.domain.Pageable;

public interface SupportTicketService {
	
	SupportTicketDto getSupportTicketById(Long id);

	SupportTicketDto createSupportTicket(SupportTicketDto supportTicketDto);
	
	CustomPage<SupportTicketDto> searchSupportTickets(Pageable pageable, Map<String, String> params);

	void deleteSupportTickets(IdsDto idsDto);

	SupportTicketAssignmentStrategyDto getSupportTicketAssignmentStrategy();

	void configureSupportTicketAssignmentStrategy(SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto);

}
