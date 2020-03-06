package com.app.ecom.store.support.resource;

import com.app.ecom.store.support.constants.Endpoint;
import com.app.ecom.store.support.dto.SupportTicketActivityHistoryDto;
import com.app.ecom.store.support.service.SupportTicketActivityHistoryService;
import com.app.ecom.store.support.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportTicketActivityHistoryResource {
	
	private static final Logger logger = LogManager.getLogger(SupportTicketActivityHistoryResource.class);
	
	@Autowired
	private SupportTicketActivityHistoryService supportTicketActivityHistoryService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.TICKET_ACTIVITY)
	public ResponseEntity<SupportTicketActivityHistoryDto> postSupportTicketActivity(@RequestBody SupportTicketActivityHistoryDto supportTicketActivityHistoryDto) {
		try {
			SupportTicketActivityHistoryDto createdSupportTicketActivityHistoryDto = supportTicketActivityHistoryService.postSupportTicketActivity(supportTicketActivityHistoryDto);
			return new ResponseEntity<>(createdSupportTicketActivityHistoryDto, supportTicketActivityHistoryDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while adding support ticket activity: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
