package com.app.ecom.store.support.resource;

import com.app.ecom.store.support.constants.Endpoint;
import com.app.ecom.store.support.dto.SupportTicketAssignmentStrategyDto;
import com.app.ecom.store.support.service.SupportTicketAssignmentStrategyService;
import com.app.ecom.store.support.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportTicketAssignmentStrategyResource {

private static final Logger logger = LogManager.getLogger(SupportTicketAssignmentStrategyResource.class);
	
	@Autowired
	private SupportTicketAssignmentStrategyService supportTicketAssignmentStrategyService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.TICKET_ASSIGNMENT_STRATEGY)
	public ResponseEntity<SupportTicketAssignmentStrategyDto> configureSupportTicketAssignmentStrategy(@RequestBody SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto) {
		try {
			SupportTicketAssignmentStrategyDto createdSupportTicketAssignmentStrategyDto = supportTicketAssignmentStrategyService.configureSupportTicketAssignmentStrategy(supportTicketAssignmentStrategyDto);
			return new ResponseEntity<>(createdSupportTicketAssignmentStrategyDto, supportTicketAssignmentStrategyDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while configure support ticket assignment strategy: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = Endpoint.TICKET_ASSIGNMENT_STRATEGY)
	public ResponseEntity<SupportTicketAssignmentStrategyDto> getSupportTicketAssignmentStrategy() {
		try {
			SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto = supportTicketAssignmentStrategyService.getSupportTicketAssignmentStrategy();
			return new ResponseEntity<>(supportTicketAssignmentStrategyDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting support ticket assignment strategy: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
