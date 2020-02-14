package com.app.ecom.store.support.service.impl;

import java.util.List;
import java.util.Optional;

import com.app.ecom.store.support.dto.IdsDto;
import com.app.ecom.store.support.dto.QueryRequest;
import com.app.ecom.store.support.dto.SupportTicketDto;
import com.app.ecom.store.support.dto.SupportTicketDtos;
import com.app.ecom.store.support.dto.SupportTicketSearchRequest;
import com.app.ecom.store.support.enums.SupportTicketStatus;
import com.app.ecom.store.support.handler.QueryHandler;
import com.app.ecom.store.support.mapper.SupportTicketMapper;
import com.app.ecom.store.support.model.SupportTicket;
import com.app.ecom.store.support.repository.SupportTicketRepository;
import com.app.ecom.store.support.service.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class SupportTicketServiceImpl implements SupportTicketService {

	@Autowired
	private SupportTicketRepository supportTicketRepository;

	@Autowired
	private SupportTicketMapper supportTicketMapper;

	@Autowired
	private QueryHandler<SupportTicket> queryHandler;
	
	@Override
	@Transactional
	public SupportTicketDto createModifySupportTicket(SupportTicketDto supportTicketDto) {
		SupportTicket supportTicket = supportTicketRepository.save(supportTicketMapper.supportTicketDtoToSupportTicket(supportTicketDto));
		return supportTicketMapper.supportTicketToSupportTicketDto(supportTicket);
	}
	
	@Override
	public SupportTicketDto getSupportTicketById(Long id) {
		Optional<SupportTicket> optionalSupportTicket = supportTicketRepository.findById(id);
		return optionalSupportTicket.isPresent() ? supportTicketMapper.supportTicketToSupportTicketDto(optionalSupportTicket.get()) : null;
	}

	@Override
	public SupportTicketDtos getSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest) {
		queryHandler.setType(SupportTicket.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(supportTicketMapper.supportTicketSearchRequestToWhereClauses(supportTicketSearchRequest));
		queryRequest.setOrderByClauses(supportTicketSearchRequest.getOrderByClauses());
		queryRequest.setOffset(supportTicketSearchRequest.getOffset());
		queryRequest.setLimit(supportTicketSearchRequest.getLimit());
		List<SupportTicket> supportTickets = queryHandler.findByQueryRequest(queryRequest);
		return supportTicketMapper.supportTicketsToSupportTicketDtos(supportTickets);
	}			
	
	@Override
	public SupportTicketDtos getUnclosedSupportTickets(String username) {
		return supportTicketMapper.supportTicketsToSupportTicketDtos(supportTicketRepository.findByAssignedToAndCreatedByNotAndStatusNot(username, username, SupportTicketStatus.CLOSED.name()));
	}
	
	@Override
	public Long countSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest) {
		queryHandler.setType(SupportTicket.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(supportTicketMapper.supportTicketSearchRequestToWhereClauses(supportTicketSearchRequest));
		return queryHandler.countByQueryRequest(queryRequest, "id");
	}
	
	@Override
	public Long countUnclosedSupportTickets(String username) {
		return supportTicketRepository.countByAssignedToAndCreatedByNotAndStatusNot(username, username, SupportTicketStatus.CLOSED.name());
	}

	@Override
	@Transactional
	public void deleteSupportTickets(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			supportTicketRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			supportTicketRepository.deleteById(idsDto.getIds().get(0));
		} else {
			supportTicketRepository.deleteByIdIn(idsDto.getIds());
		}
	}

	@Override
	@Transactional
	public void changeSupportTicketStatus(Long ticketId, String status) {
		supportTicketRepository.updateSupportTicketStatus(ticketId, status);
	}

	@Override
	@Transactional
	public void changeSupportTicketPriority(Long ticketId, String priority) {
		supportTicketRepository.updateSupportTicketPriority(ticketId, priority);
	}
	
	@Override
	@Transactional
	public void changeSupportTicketStatusAndAssignedTo(Long ticketId, String status, String assignedTo) {
		supportTicketRepository.updateSupportTicketStatusAndAssignedTo(status, assignedTo, ticketId);
	}
}