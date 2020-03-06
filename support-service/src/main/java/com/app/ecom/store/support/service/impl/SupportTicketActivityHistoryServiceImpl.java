package com.app.ecom.store.support.service.impl;

import javax.transaction.Transactional;

import com.app.ecom.store.support.dto.SupportTicketActivityHistoryDto;
import com.app.ecom.store.support.mapper.SupportTicketActivityHistoryMapper;
import com.app.ecom.store.support.repository.SupportTicketActivityHistoryRepository;
import com.app.ecom.store.support.service.SupportTicketActivityHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportTicketActivityHistoryServiceImpl implements SupportTicketActivityHistoryService {
	
	@Autowired
	private SupportTicketActivityHistoryMapper supportTicketActivityHistoryMapper;
	
	@Autowired
	private SupportTicketActivityHistoryRepository supportTicketActivityHistoryRepository;

	@Override
	@Transactional
	public SupportTicketActivityHistoryDto postSupportTicketActivity(SupportTicketActivityHistoryDto supportTicketActivityHistoryDto) {
		return supportTicketActivityHistoryMapper.supportTicketActivityHistoryToSupportTicketActivityHistoryDto(
				supportTicketActivityHistoryRepository.save(supportTicketActivityHistoryMapper.
						supportTicketActivityHistoryDtoToSupportTicketActivityHistory(supportTicketActivityHistoryDto)));
	}

}
