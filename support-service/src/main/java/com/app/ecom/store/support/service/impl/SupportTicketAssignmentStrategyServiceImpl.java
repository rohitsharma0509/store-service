package com.app.ecom.store.support.service.impl;

import java.util.List;

import com.app.ecom.store.support.dto.SupportTicketAssignmentStrategyDto;
import com.app.ecom.store.support.mapper.SupportTicketAssignmentStrategyMapper;
import com.app.ecom.store.support.repository.SupportTicketAssignmentStrategy;
import com.app.ecom.store.support.repository.SupportTicketAssignmentStrategyRepository;
import com.app.ecom.store.support.service.SupportTicketAssignmentStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SupportTicketAssignmentStrategyServiceImpl implements SupportTicketAssignmentStrategyService {
	
	@Autowired
	private SupportTicketAssignmentStrategyRepository supportTicketAssignmentStrategyRepository;
	
	@Autowired
	private SupportTicketAssignmentStrategyMapper supportTicketAssignmentStrategyMapper;

	@Override
	public SupportTicketAssignmentStrategyDto configureSupportTicketAssignmentStrategy(
			SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto) {
		return supportTicketAssignmentStrategyMapper.supportTicketAssignmentStrategyToSupportTicketAssignmentStrategyDto(
				supportTicketAssignmentStrategyRepository.save(supportTicketAssignmentStrategyMapper.
						supportTicketAssignmentStrategyDtoToSupportTicketAssignmentStrategy(supportTicketAssignmentStrategyDto)));
	}

	@Override
	public SupportTicketAssignmentStrategyDto getSupportTicketAssignmentStrategy() {
		List<SupportTicketAssignmentStrategy> strategies = supportTicketAssignmentStrategyRepository.findAll();
		return CollectionUtils.isEmpty(strategies) ? new SupportTicketAssignmentStrategyDto() : supportTicketAssignmentStrategyMapper.supportTicketAssignmentStrategyToSupportTicketAssignmentStrategyDto(strategies.get(0));
	}

}
