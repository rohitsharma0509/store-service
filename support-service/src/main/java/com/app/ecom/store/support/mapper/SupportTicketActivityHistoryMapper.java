package com.app.ecom.store.support.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.app.ecom.store.support.dto.SupportTicketActivityHistoryDto;
import com.app.ecom.store.support.model.SupportTicket;
import com.app.ecom.store.support.model.SupportTicketActivityHistory;
import com.app.ecom.store.support.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class SupportTicketActivityHistoryMapper {
	
	@Autowired
	private SupportTicketRepository supportTicketRepository;
	
	public List<SupportTicketActivityHistoryDto> supportTicketActivityHistoriesToSupportTicketActivityHistoryDtos(Set<SupportTicketActivityHistory> supportTicketActivityHistories) {
		if(CollectionUtils.isEmpty(supportTicketActivityHistories)) {
			return Collections.emptyList();
		}
		
		List<SupportTicketActivityHistoryDto> listOfActivityHistory = new ArrayList<>();
		supportTicketActivityHistories.stream().forEach(supportTicketActivityHistory -> listOfActivityHistory.add(supportTicketActivityHistoryToSupportTicketActivityHistoryDto(supportTicketActivityHistory)));
		return listOfActivityHistory;
	}

	public SupportTicketActivityHistoryDto supportTicketActivityHistoryToSupportTicketActivityHistoryDto(SupportTicketActivityHistory supportTicketActivityHistory) {
		if(supportTicketActivityHistory == null) {
			return null;
		}
		
		SupportTicketActivityHistoryDto supportTicketActivityHistoryDto = new SupportTicketActivityHistoryDto();
		supportTicketActivityHistoryDto.setId(supportTicketActivityHistory.getId());
		supportTicketActivityHistoryDto.setMessage(supportTicketActivityHistory.getMessage());
		supportTicketActivityHistoryDto.setCreatedBy(supportTicketActivityHistory.getCreatedBy());
		supportTicketActivityHistoryDto.setCreatedTs(supportTicketActivityHistory.getCreatedTs());
		return supportTicketActivityHistoryDto;
	}
	
	public SupportTicketActivityHistory supportTicketActivityHistoryDtoToSupportTicketActivityHistory(SupportTicketActivityHistoryDto supportTicketActivityHistoryDto) {
		if(supportTicketActivityHistoryDto == null) {
			return null;
		}
		
		SupportTicketActivityHistory supportTicketActivityHistory = new SupportTicketActivityHistory();
		supportTicketActivityHistory.setId(supportTicketActivityHistoryDto.getId());
		supportTicketActivityHistory.setMessage(supportTicketActivityHistoryDto.getMessage());
		Optional<SupportTicket> optionalSupportTicket = supportTicketRepository.findById(supportTicketActivityHistoryDto.getTicketId());
		supportTicketActivityHistory.setSupportTicket(optionalSupportTicket.isPresent() ? optionalSupportTicket.get() : null);
		supportTicketActivityHistory.setCreatedBy(supportTicketActivityHistoryDto.getCreatedBy());
		supportTicketActivityHistory.setCreatedTs(supportTicketActivityHistoryDto.getCreatedTs());
		return supportTicketActivityHistory;
	}
	
}
