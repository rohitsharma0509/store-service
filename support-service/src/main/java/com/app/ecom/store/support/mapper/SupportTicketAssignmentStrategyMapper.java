package com.app.ecom.store.support.mapper;

import com.app.ecom.store.support.dto.SupportTicketAssignmentStrategyDto;
import com.app.ecom.store.support.repository.SupportTicketAssignmentStrategy;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketAssignmentStrategyMapper {
	
	public SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyToSupportTicketAssignmentStrategyDto(SupportTicketAssignmentStrategy supportTicketAssignmentStrategy) {
		if(supportTicketAssignmentStrategy == null) {
			return null;
		}
		
		SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto = new SupportTicketAssignmentStrategyDto();
		supportTicketAssignmentStrategyDto.setId(supportTicketAssignmentStrategy.getId());
		supportTicketAssignmentStrategyDto.setType(supportTicketAssignmentStrategy.getType());
		supportTicketAssignmentStrategyDto.setUserId(supportTicketAssignmentStrategy.getUserId());
		supportTicketAssignmentStrategyDto.setRoleId(supportTicketAssignmentStrategy.getRoleId());
		supportTicketAssignmentStrategyDto.setLastModifiedBy(supportTicketAssignmentStrategy.getLastModifiedBy());
		supportTicketAssignmentStrategyDto.setLastModifiedTs(supportTicketAssignmentStrategy.getLastModifiedTs());
		return supportTicketAssignmentStrategyDto;
	}
	
	public SupportTicketAssignmentStrategy supportTicketAssignmentStrategyDtoToSupportTicketAssignmentStrategy(SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto) {
		if(supportTicketAssignmentStrategyDto == null) {
			return null;
		}
		
		SupportTicketAssignmentStrategy supportTicketAssignmentStrategy = new SupportTicketAssignmentStrategy();
		supportTicketAssignmentStrategy.setId(supportTicketAssignmentStrategyDto.getId());
		supportTicketAssignmentStrategy.setType(supportTicketAssignmentStrategyDto.getType());
		supportTicketAssignmentStrategy.setUserId(supportTicketAssignmentStrategyDto.getUserId());
		supportTicketAssignmentStrategy.setRoleId(supportTicketAssignmentStrategyDto.getRoleId());
		supportTicketAssignmentStrategy.setLastModifiedBy(supportTicketAssignmentStrategyDto.getLastModifiedBy());
		supportTicketAssignmentStrategy.setLastModifiedTs(supportTicketAssignmentStrategyDto.getLastModifiedTs());
		return supportTicketAssignmentStrategy;
	}

}
