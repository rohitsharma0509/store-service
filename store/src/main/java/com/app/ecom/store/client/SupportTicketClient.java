package com.app.ecom.store.client;

import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.helpservice.SupportTicketDto;
import com.app.ecom.store.dto.helpservice.SupportTicketDtos;
import com.app.ecom.store.dto.helpservice.SupportTicketSearchRequest;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketClient {
	
	private static final String TICKET = "/ticket";
	private static final String COUNT_TICKET = "/countTicket";
	
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
	
	public SupportTicketDtos getSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET);
		ExternalApiRequest<SupportTicketDtos> externalApi = commonUtil.getExternalApiRequest(SupportTicketDtos.class, url, HttpMethod.POST, null, null, supportTicketSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countSupportTickets(SupportTicketSearchRequest supportTicketSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_TICKET);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, supportTicketSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteSupportTickets(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TICKET);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}

}
