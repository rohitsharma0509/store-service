package com.app.ecom.store.support.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.app.ecom.store.support.dto.SupportTicketDto;
import com.app.ecom.store.support.dto.SupportTicketDtos;
import com.app.ecom.store.support.dto.SupportTicketSearchRequest;
import com.app.ecom.store.support.dto.WhereClause;
import com.app.ecom.store.support.enums.OperationType;
import com.app.ecom.store.support.enums.Priority;
import com.app.ecom.store.support.enums.SupportTicketStatus;
import com.app.ecom.store.support.model.SupportTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class SupportTicketMapper {
	
	@Autowired
	private SupportTicketStatusChangeHistoryMapper supportTicketStatusChangeHistoryMapper;
	
	@Autowired
	private SupportTicketActivityHistoryMapper supportTicketActivityHistoryMapper;
	
	public SupportTicketDtos supportTicketsToSupportTicketDtos(List<SupportTicket> supportTickets) {
		SupportTicketDtos supportTicketDtos = new SupportTicketDtos();
		List<SupportTicketDto> listOfSupportTicketDto = new ArrayList<>();
		if(!CollectionUtils.isEmpty(supportTickets)) {
			supportTickets.stream().forEach(supportTicket -> listOfSupportTicketDto.add(supportTicketToSupportTicketDto(supportTicket)));
		}
		supportTicketDtos.setSupportTickets(listOfSupportTicketDto);
		return supportTicketDtos;
	}

	
	public SupportTicketDto supportTicketToSupportTicketDto(SupportTicket supportTicket) {
		if(null == supportTicket) {
			return null;
		}
		
		SupportTicketDto supportTicketDto = new SupportTicketDto();
		supportTicketDto.setId(supportTicket.getId());
		supportTicketDto.setNumber(supportTicket.getNumber());
		supportTicketDto.setDescription(supportTicket.getDescription());
		supportTicketDto.setOrderNumber(supportTicket.getOrderNumber());
		supportTicketDto.setPriority(supportTicket.getPriority() == null ? null : Priority.valueOf(supportTicket.getPriority()));
		supportTicketDto.setStatus(supportTicket.getStatus() == null ? null : SupportTicketStatus.valueOf(supportTicket.getStatus()));
		supportTicketDto.setAssignedTo(supportTicket.getAssignedTo());
		supportTicketDto.setCreatedBy(supportTicket.getCreatedBy());
		supportTicketDto.setCreatedTs(supportTicket.getCreatedTs());
		supportTicketDto.setLastModifiedBy(supportTicket.getLastModifiedBy());
		supportTicketDto.setLastModifiedTs(supportTicket.getLastModifiedTs());
		supportTicketDto.setSupportTicketActivityHistoryDtos(supportTicketActivityHistoryMapper.supportTicketActivityHistoriesToSupportTicketActivityHistoryDtos(supportTicket.getSupportTicketActivityHistories()));
		supportTicketDto.setSupportTicketStatusChangeHistoryDtos(supportTicketStatusChangeHistoryMapper.supportTicketStatusChangeHistoriesToSupportTicketStatusChangeHistoryDtos(supportTicket.getSupportTicketStatusChangeHistories()));
		return supportTicketDto;
	}

	public Set<SupportTicket> supportTicketDtosToSupportTickets(Set<SupportTicketDto> supportTicketDtos) {		
		if(CollectionUtils.isEmpty(supportTicketDtos)) {
			return Collections.emptySet();
		}
		
		Set<SupportTicket> supportTickets = new HashSet<>();
		supportTicketDtos.stream().forEach(supportTicketDto -> supportTickets.add(supportTicketDtoToSupportTicket(supportTicketDto)));
		return supportTickets;
	}

	
	public SupportTicket supportTicketDtoToSupportTicket(SupportTicketDto supportTicketDto) {
		if(null == supportTicketDto) {
			return null;
		}
		
		SupportTicket supportTicket = new SupportTicket();
		supportTicket.setId(supportTicketDto.getId());
		supportTicket.setNumber(supportTicketDto.getNumber());
		supportTicket.setOrderNumber(supportTicketDto.getOrderNumber());
		supportTicket.setDescription(supportTicketDto.getDescription());
		supportTicket.setPriority(supportTicketDto.getPriority() == null ? null : supportTicketDto.getPriority().name());
		supportTicket.setStatus(supportTicketDto.getStatus() == null ? null : supportTicketDto.getStatus().name());
		supportTicket.setAssignedTo(supportTicketDto.getAssignedTo());
		supportTicket.setCreatedBy(supportTicketDto.getCreatedBy());
		supportTicket.setCreatedTs(supportTicketDto.getCreatedTs());
		supportTicket.setLastModifiedBy(supportTicketDto.getLastModifiedBy());
		supportTicket.setLastModifiedTs(supportTicketDto.getLastModifiedTs());
		return supportTicket;
	}
	
	public List<WhereClause> supportTicketSearchRequestToWhereClauses(SupportTicketSearchRequest supportTicketSearchRequest) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (supportTicketSearchRequest.getTicketId() != null) {
			WhereClause whereClause = new WhereClause("id", String.valueOf(supportTicketSearchRequest.getTicketId()), OperationType.EQUALS);
			whereClauses.add(whereClause);
		} else {
			if (!StringUtils.isEmpty(supportTicketSearchRequest.getTicketNumber())) {
				WhereClause whereClause = new WhereClause("number", supportTicketSearchRequest.getTicketNumber(), OperationType.LIKE);
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(supportTicketSearchRequest.getOrderNumber())) {
				WhereClause whereClause = new WhereClause("orderNumber", supportTicketSearchRequest.getOrderNumber(), OperationType.LIKE);
				whereClauses.add(whereClause);
			}
			if (null != supportTicketSearchRequest.getStatus()) {
				WhereClause whereClause = new WhereClause("status", supportTicketSearchRequest.getStatus().name(), OperationType.EQUALS);
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(supportTicketSearchRequest.getCreatedBy())) {
				WhereClause whereClause = new WhereClause("createdBy", supportTicketSearchRequest.getCreatedBy(), OperationType.EQUALS);
				whereClauses.add(whereClause);
			}
			if (null != supportTicketSearchRequest.getCreatedTs()) {
				WhereClause whereClause = new WhereClause("createdTs", supportTicketSearchRequest.getCreatedTs(), OperationType.LESS_OR_EQUAL);
				whereClauses.add(whereClause);
			}
		}
		return whereClauses;
	}
}
