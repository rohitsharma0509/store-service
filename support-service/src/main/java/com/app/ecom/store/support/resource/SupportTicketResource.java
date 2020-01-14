package com.app.ecom.store.support.resource;

import com.app.ecom.store.support.constants.Endpoint;
import com.app.ecom.store.support.dto.IdsDto;
import com.app.ecom.store.support.dto.SupportTicketDto;
import com.app.ecom.store.support.dto.SupportTicketDtos;
import com.app.ecom.store.support.dto.SupportTicketSearchRequest;
import com.app.ecom.store.support.service.SupportTicketService;
import com.app.ecom.store.support.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportTicketResource {
	
	private static final Logger logger = LogManager.getLogger(SupportTicketResource.class);
	
	@Autowired
	private SupportTicketService supportTicketService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.TICKET)
	public ResponseEntity<SupportTicketDto> createModifySupportTicket(@RequestBody SupportTicketDto supportTicketDto) {
		try {
			SupportTicketDto createdSupportTicketDto = supportTicketService.createModifySupportTicket(supportTicketDto);
			return new ResponseEntity<>(createdSupportTicketDto, supportTicketDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while adding support ticket: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.TICKET)
	public ResponseEntity<SupportTicketDtos> getSupportTickets(@RequestBody SupportTicketSearchRequest supportTicketSearchRequest) {
		try {
			SupportTicketDtos supportTicketDtos = supportTicketService.getSupportTickets(supportTicketSearchRequest);
			return new ResponseEntity<>(supportTicketDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting Support Tickets: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_TICKET)
	public ResponseEntity<Long> countSupportTickets(@RequestBody SupportTicketSearchRequest supportTicketSearchRequest) {
		try {
			Long noOfSupportTickets = supportTicketService.countSupportTickets(supportTicketSearchRequest);
			return new ResponseEntity<>(noOfSupportTickets, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting Support Ticket: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.TICKET)
	public ResponseEntity<Void> deleteSupportTickets(@RequestBody IdsDto idsDto) {
		try {
			supportTicketService.deleteSupportTickets(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting Support Tickets: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
