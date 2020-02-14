package com.app.ecom.store.support.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.app.ecom.store.support.dto.SupportTicketStatusChangeHistoryDto;
import com.app.ecom.store.support.model.SupportTicketStatusChangeHistory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class SupportTicketStatusChangeHistoryMapper {
	
	public List<SupportTicketStatusChangeHistoryDto> supportTicketStatusChangeHistoriesToSupportTicketStatusChangeHistoryDtos(
			Set<SupportTicketStatusChangeHistory> supportTicketStatusChangeHistories) {
		if(CollectionUtils.isEmpty(supportTicketStatusChangeHistories)) {
			return Collections.emptyList();
		}
		
		List<SupportTicketStatusChangeHistoryDto> listOfStatusChangeHistory = new ArrayList<>();
		supportTicketStatusChangeHistories.stream().forEach(supportTicketStatusChangeHistory -> listOfStatusChangeHistory.add(supportTicketStatusChangeHistoryToSupportTicketStatusChangeHistoryDto(supportTicketStatusChangeHistory)));
		return listOfStatusChangeHistory;
	}


	public SupportTicketStatusChangeHistoryDto supportTicketStatusChangeHistoryToSupportTicketStatusChangeHistoryDto(
			SupportTicketStatusChangeHistory supportTicketStatusChangeHistory) {
		if(supportTicketStatusChangeHistory == null) {
			return null;
		}
		
		SupportTicketStatusChangeHistoryDto supportTicketStatusChangeHistoryDto = new SupportTicketStatusChangeHistoryDto();
		supportTicketStatusChangeHistoryDto.setId(supportTicketStatusChangeHistory.getId());
		supportTicketStatusChangeHistoryDto.setFrom(supportTicketStatusChangeHistory.getFrom());
		supportTicketStatusChangeHistoryDto.setTo(supportTicketStatusChangeHistory.getTo());
		supportTicketStatusChangeHistoryDto.setCreatedBy(supportTicketStatusChangeHistory.getCreatedBy());
		supportTicketStatusChangeHistoryDto.setCreatedTs(supportTicketStatusChangeHistory.getCreatedTs());
		return supportTicketStatusChangeHistoryDto;
	}
	
	public SupportTicketStatusChangeHistory supportTicketStatusChangeHistoryDtoToSupportTicketStatusChangeHistory(
			SupportTicketStatusChangeHistoryDto supportTicketStatusChangeHistoryDto) {
		if(supportTicketStatusChangeHistoryDto == null) {
			return null;
		}
		
		SupportTicketStatusChangeHistory supportTicketStatusChangeHistory = new SupportTicketStatusChangeHistory();
		supportTicketStatusChangeHistory.setId(supportTicketStatusChangeHistoryDto.getId());
		supportTicketStatusChangeHistory.setFrom(supportTicketStatusChangeHistoryDto.getFrom());
		supportTicketStatusChangeHistory.setTo(supportTicketStatusChangeHistoryDto.getTo());
		supportTicketStatusChangeHistory.setCreatedBy(supportTicketStatusChangeHistoryDto.getCreatedBy());
		supportTicketStatusChangeHistory.setCreatedTs(supportTicketStatusChangeHistoryDto.getCreatedTs());
		return supportTicketStatusChangeHistory;
	}

}
