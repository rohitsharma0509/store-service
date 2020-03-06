package com.app.ecom.store.client;

import java.util.HashMap;
import java.util.Map;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.supportservice.SupportTicketActivityHistoryDto;
import com.app.ecom.store.dto.supportservice.SupportTicketAssignmentStrategyDto;
import com.app.ecom.store.dto.supportservice.SupportTicketDto;
import com.app.ecom.store.dto.supportservice.SupportTicketDtos;
import com.app.ecom.store.dto.supportservice.SupportTicketReportByStatus;
import com.app.ecom.store.dto.supportservice.SupportTicketSearchRequest;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketClient {
	
	private static final String TICKET = "/ticket";
	private static final String COUNT_TICKET = "/countTicket";
	public static final String UNCLOSED_TICKET = "/unclosedTicket";
	public static final String COUNT_UNCLOSED_TICKET = "/countUnclosedTicket";
	public static final String TICKET_ASSIGNMENT_STRATEGY = "/ticketAssignmentStrategy";
	public static final String TICKET_STATUS_REPORT = "/ticketStatusReport";
	public static final String TICKET_ACTIVITY = "/ticketActivity";
	
	@Value("${application.support-service.name}")
	private String serviceName;
	
	@Value("${application.support-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;

	public SupportTicketDto createSupportTicket(SupportTicketDto supportTicketDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET);
		ExternalApiRequest<SupportTicketDto> externalApi = commonUtil.getExternalApiRequest(SupportTicketDto.class, url, HttpMethod.PUT, null, null, supportTicketDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public SupportTicketDto getSupportTicket(Long id) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET+"/"+id);
		ExternalApiRequest<SupportTicketDto> externalApi = commonUtil.getExternalApiRequest(SupportTicketDto.class, url, HttpMethod.GET, null, null, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public SupportTicketDtos getSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET);
		ExternalApiRequest<SupportTicketDtos> externalApi = commonUtil.getExternalApiRequest(SupportTicketDtos.class, url, HttpMethod.POST, null, null, supportTicketSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public SupportTicketDtos getUnclosedSupportTickets(String username) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, UNCLOSED_TICKET);
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put(FieldNames.USERNAME, username);
		ExternalApiRequest<SupportTicketDtos> externalApi = commonUtil.getExternalApiRequest(SupportTicketDtos.class, url, HttpMethod.GET, null, queryParamMap, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_TICKET);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, supportTicketSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countUnclosedSupportTickets(String username) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_UNCLOSED_TICKET);
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put(FieldNames.USERNAME, username);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.GET, null, queryParamMap, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteSupportTickets(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}

	public SupportTicketAssignmentStrategyDto configureSupportTicketAssignmentStrategy(
			SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET_ASSIGNMENT_STRATEGY);
		ExternalApiRequest<SupportTicketAssignmentStrategyDto> externalApi = commonUtil.getExternalApiRequest(SupportTicketAssignmentStrategyDto.class, url, HttpMethod.PUT, null, null, supportTicketAssignmentStrategyDto);
		return externalApiHandler.callExternalApi(externalApi);
	}

	public SupportTicketAssignmentStrategyDto getSupportTicketAssignmentStrategy() {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET_ASSIGNMENT_STRATEGY);
		ExternalApiRequest<SupportTicketAssignmentStrategyDto> externalApi = commonUtil.getExternalApiRequest(SupportTicketAssignmentStrategyDto.class, url, HttpMethod.GET, null, null, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public SupportTicketReportByStatus getSupportTicketReportByStatus(String username) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET_STATUS_REPORT);
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put(FieldNames.USERNAME, username);
		ExternalApiRequest<SupportTicketReportByStatus> externalApi = commonUtil.getExternalApiRequest(SupportTicketReportByStatus.class, url, HttpMethod.GET, null, queryParamMap, null);
		return externalApiHandler.callExternalApi(externalApi);
	}

	public SupportTicketActivityHistoryDto postSupportTicketActivity(SupportTicketActivityHistoryDto supportTicketActivityHistoryDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET_ACTIVITY);
		ExternalApiRequest<SupportTicketActivityHistoryDto> externalApi = commonUtil.getExternalApiRequest(SupportTicketActivityHistoryDto.class, url, HttpMethod.PUT, null, null, supportTicketActivityHistoryDto);
		return externalApiHandler.callExternalApi(externalApi);
	}

}
