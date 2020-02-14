package com.app.ecom.store.support.service;

import com.app.ecom.store.support.dto.SupportTicketAssignmentStrategyDto;

public interface SupportTicketAssignmentStrategyService {

	SupportTicketAssignmentStrategyDto configureSupportTicketAssignmentStrategy(
			SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto);

	SupportTicketAssignmentStrategyDto getSupportTicketAssignmentStrategy();

}
