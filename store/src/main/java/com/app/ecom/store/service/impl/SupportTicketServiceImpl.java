package com.app.ecom.store.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.client.SupportTicketClient;
import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.OrderByClause;
import com.app.ecom.store.dto.helpservice.SupportTicketDto;
import com.app.ecom.store.dto.helpservice.SupportTicketDtos;
import com.app.ecom.store.dto.helpservice.SupportTicketSearchRequest;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.enums.SortOrder;
import com.app.ecom.store.enums.SupportTicketStatus;
import com.app.ecom.store.service.SupportTicketService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
		SupportTicketSearchRequest supportTicketSearchRequest = new SupportTicketSearchRequest();
		supportTicketSearchRequest.setTicketId(id);
		SupportTicketDtos supportTicketDtos = supportTicketClient.getSupportTickets(supportTicketSearchRequest);
		if(null != supportTicketDtos && !CollectionUtils.isEmpty(supportTicketDtos.getSupportTickets())) {
			Optional<SupportTicketDto> optional = supportTicketDtos.getSupportTickets().stream().filter(Objects::nonNull).findFirst();
			return optional.isPresent() ? optional.get() : null;
		} else {
			return null;
		}
	}

	@Override
	public SupportTicketDto createSupportTicket(SupportTicketDto supportTicketDto) {
		supportTicketDto.setStatus(SupportTicketStatus.OPEN);
		supportTicketDto.setNumber(commonUtil.generateRandomDigits("TKT", 10, ""));
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		if(supportTicketDto.getId() != null) {
			SupportTicketDto existingSupportTicketDto = getSupportTicketById(supportTicketDto.getId());
			supportTicketDto.setCreatedBy(existingSupportTicketDto.getCreatedBy());
			supportTicketDto.setCreatedTs(existingSupportTicketDto.getCreatedTs());
			supportTicketDto.setLastModifiedBy(userDto.getUsername());
			supportTicketDto.setLastModifiedTs(ZonedDateTime.now());
		} else {
			supportTicketDto.setCreatedBy(userDto.getUsername());
			supportTicketDto.setCreatedTs(ZonedDateTime.now());
			supportTicketDto.setLastModifiedBy(userDto.getUsername());
			supportTicketDto.setLastModifiedTs(ZonedDateTime.now());
		}
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
		SupportTicketDtos supportTicketDtos = supportTicketClient.getSupportTickets(supportTicketSearchRequest);
		
		Long totalRecords = supportTicketClient.countSupportTickets(supportTicketSearchRequest);
		CustomPage<SupportTicketDto> page = new CustomPage<>();
		page.setContent(supportTicketDtos != null ? supportTicketDtos.getSupportTickets() : null);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalRecords(totalRecords == null ? 0 : totalRecords.intValue());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}
}
