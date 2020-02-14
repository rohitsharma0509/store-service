package com.app.ecom.store.support.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.app.ecom.store.support.client.UserServiceClient;
import com.app.ecom.store.support.dto.QueryRequest;
import com.app.ecom.store.support.dto.SupportTicketAssignmentStrategyDto;
import com.app.ecom.store.support.dto.WhereClause;
import com.app.ecom.store.support.dto.userservice.RoleDto;
import com.app.ecom.store.support.dto.userservice.UserDto;
import com.app.ecom.store.support.enums.OperationType;
import com.app.ecom.store.support.enums.SupportTicketStatus;
import com.app.ecom.store.support.handler.QueryHandler;
import com.app.ecom.store.support.model.SupportTicket;
import com.app.ecom.store.support.service.SupportTicketAssignmentStrategyService;
import com.app.ecom.store.support.service.SupportTicketService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketAssignmentScheduler {
	
	private static final Logger logger = LogManager.getLogger(SupportTicketAssignmentScheduler.class);
	
	@Autowired
	private QueryHandler<SupportTicket> queryHandler;
	
	@Autowired
	private SupportTicketService supportTicketService;
	
	@Autowired
	private SupportTicketAssignmentStrategyService supportTicketAssignmentStrategyService;
	
	@Autowired
	private UserServiceClient userServiceClient;

	@Scheduled(cron = "${job.support-ticket-assignment.cron}")
	public void assignSupportTickets() {
		queryHandler.setType(SupportTicket.class);
		QueryRequest queryRequest = new QueryRequest();
		List<WhereClause> whereClauses = new ArrayList<>();
		whereClauses.add(new WhereClause("assignedTo", null, OperationType.NULL));
		queryRequest.setWhereClauses(whereClauses);
		
		List<SupportTicket> unassignedSupportTickets = queryHandler.findByQueryRequest(queryRequest);
		updateStatusAndAssignedTo(unassignedSupportTickets);
	}
	

	private void updateStatusAndAssignedTo(List<SupportTicket> unassignedSupportTickets) {
		if(!CollectionUtils.isEmpty(unassignedSupportTickets)) {
			SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto = supportTicketAssignmentStrategyService.getSupportTicketAssignmentStrategy();
			if(null != supportTicketAssignmentStrategyDto) {
				logger.info(new StringBuilder("Support ticket assignment started. No. of unassigned support tickets: ").append(unassignedSupportTickets.size()).append(" Time: ").append(new Date()).toString());
				unassignedSupportTickets.stream().filter(Objects::nonNull).forEach(unassignedSupportTicket -> {
					String assignedTo = "";
					if(null != supportTicketAssignmentStrategyDto.getRoleId()) {
						RoleDto roleDto = userServiceClient.getRoleByRoleId(supportTicketAssignmentStrategyDto.getRoleId());
						assignedTo = CollectionUtils.isEmpty(roleDto.getUserDtos()) ? null : roleDto.getUserDtos().get(0).getUsername();
					} else if(null != supportTicketAssignmentStrategyDto.getUserId()) {
						UserDto userDto = userServiceClient.getUserByUserId(supportTicketAssignmentStrategyDto.getUserId());
						assignedTo = userDto.getUsername();
					}
					logger.info(new StringBuilder("Ticket Id: ").append(unassignedSupportTicket.getId()).append(" Assigned To: ").append(assignedTo).append(new Date()).toString());
					supportTicketService.changeSupportTicketStatusAndAssignedTo(unassignedSupportTicket.getId(), SupportTicketStatus.IN_PROGRESS.name(), assignedTo);
				});
			} else {
				logger.info(new StringBuilder("Support ticket assignment strategy not configured.").append(" Time: ").append(new Date()).toString());
			}
		}
	}	
}
