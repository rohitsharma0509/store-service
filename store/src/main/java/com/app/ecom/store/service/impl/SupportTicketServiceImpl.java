package com.app.ecom.store.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.client.SupportTicketClient;
import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.OrderByClause;
import com.app.ecom.store.dto.supportservice.SupportTicketAssignmentStrategyDto;
import com.app.ecom.store.dto.supportservice.SupportTicketDto;
import com.app.ecom.store.dto.supportservice.SupportTicketDtos;
import com.app.ecom.store.dto.supportservice.SupportTicketSearchRequest;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.enums.SortOrder;
import com.app.ecom.store.enums.SupportTicketStatus;
import com.app.ecom.store.service.SupportTicketService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SupportTicketServiceImpl implements SupportTicketService {
	
	@Autowired
	private SupportTicketClient supportTicketClient;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public SupportTicketDto getSupportTicketById(Long id) {
		return supportTicketClient.getSupportTicket(id);
	}

	@Override
	public SupportTicketDto createSupportTicket(SupportTicketDto supportTicketDto) {
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		if(supportTicketDto.getId() != null) {
			SupportTicketDto existingSupportTicketDto = getSupportTicketById(supportTicketDto.getId());
			supportTicketDto.setCreatedBy(existingSupportTicketDto.getCreatedBy());
			supportTicketDto.setCreatedTs(existingSupportTicketDto.getCreatedTs());
		} else {
			supportTicketDto.setStatus(SupportTicketStatus.OPEN);
			supportTicketDto.setNumber(commonUtil.generateRandomDigits("TKT", 10, ""));
			supportTicketDto.setCreatedBy(userDto.getUsername());
			supportTicketDto.setCreatedTs(ZonedDateTime.now());
		}
		supportTicketDto.setLastModifiedBy(userDto.getUsername());
		supportTicketDto.setLastModifiedTs(ZonedDateTime.now());
		return supportTicketClient.createSupportTicket(supportTicketDto);
	}
	
	@Override
	public CustomPage<SupportTicketDto> searchSupportTickets(Pageable pageable, Map<String, String> params) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		
		SupportTicketSearchRequest supportTicketSearchRequest = new SupportTicketSearchRequest();
		supportTicketSearchRequest.setTicketNumber(params.get(FieldNames.TICKET_NUMBER));
		supportTicketSearchRequest.setOrderNumber(params.get(FieldNames.ORDER_NUMBER));
		supportTicketSearchRequest.setCreatedTs(commonUtil.convertDateToZonedDateTime(commonUtil.convertStringToDate(params.get(FieldNames.CREATED_TS), Constants.YYYY_MM_DD)));
		supportTicketSearchRequest.setStatus(!StringUtils.isEmpty(params.get(FieldNames.STATUS)) ? SupportTicketStatus.valueOf(params.get(FieldNames.STATUS)) : null);
		
		List<OrderByClause> orderByClauses = new ArrayList<>();
		OrderByClause orderByClause = new OrderByClause();
		orderByClause.setSortBy(FieldNames.CREATED_TS);
		orderByClause.setSortOrder(SortOrder.DESC);
		orderByClauses.add(orderByClause);
		supportTicketSearchRequest.setOrderByClauses(orderByClauses);
		supportTicketSearchRequest.setOffset(offset);
		supportTicketSearchRequest.setLimit(limit);
		SupportTicketDtos supportTicketDtos;
		Long totalRecords;
		if(params.get(FieldNames.UNCLOSED_FOR) != null) {
			supportTicketDtos = supportTicketClient.getUnclosedSupportTickets(params.get(FieldNames.UNCLOSED_FOR));		
			totalRecords = supportTicketClient.countUnclosedSupportTickets(params.get(FieldNames.UNCLOSED_FOR));
		} else {
			UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
			supportTicketSearchRequest.setCreatedBy(userDto.getUsername());
			supportTicketDtos = supportTicketClient.getSupportTickets(supportTicketSearchRequest);			
			totalRecords = supportTicketClient.countSupportTickets(supportTicketSearchRequest);
		}
		
		CustomPage<SupportTicketDto> page = new CustomPage<>();
		page.setContent(supportTicketDtos != null ? supportTicketDtos.getSupportTickets() : null);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalRecords(totalRecords == null ? 0 : totalRecords.intValue());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

	@Override
	public void deleteSupportTickets(IdsDto idsDto) {
		supportTicketClient.deleteSupportTickets(idsDto);
	}

	@Override
	public SupportTicketAssignmentStrategyDto getSupportTicketAssignmentStrategy() {
		return supportTicketClient.getSupportTicketAssignmentStrategy();
	}

	@Override
	public void configureSupportTicketAssignmentStrategy(
			SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto) {
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		supportTicketAssignmentStrategyDto.setLastModifiedBy(userDto.getUsername());
		supportTicketAssignmentStrategyDto.setLastModifiedTs(ZonedDateTime.now());
		supportTicketClient.configureSupportTicketAssignmentStrategy(supportTicketAssignmentStrategyDto);
	}
}
