package com.app.ecom.store.client;

import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.templateservice.TemplateDto;
import com.app.ecom.store.dto.templateservice.TemplateDtos;
import com.app.ecom.store.dto.templateservice.TemplateSearchRequest;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class TemplateServiceClient {
	
	private static final String TEMPLATE = "/template";
	private static final String COUNT_TEMPLATE = "/countTemplate";
	
	@Value("${application.template-service.name}")
	private String serviceName;
	
	@Value("${application.template-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public TemplateDto createUpdateTemplate(TemplateDto templateDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TEMPLATE);
		ExternalApiRequest<TemplateDto> externalApi = commonUtil.getExternalApiRequest(TemplateDto.class, url, HttpMethod.PUT, null, null, templateDto);
		return externalApiHandler.callExternalApi(externalApi);
	}

	public TemplateDto getTemplate(Long id) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TEMPLATE);
		ExternalApiRequest<TemplateDto> externalApi = commonUtil.getExternalApiRequest(TemplateDto.class, url+"/"+id, HttpMethod.GET, null, null, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public TemplateDtos getTemplates(TemplateSearchRequest templateSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TEMPLATE);
		ExternalApiRequest<TemplateDtos> externalApi = commonUtil.getExternalApiRequest(TemplateDtos.class, url, HttpMethod.POST, null, null, templateSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countTemplates(TemplateSearchRequest templateSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_TEMPLATE);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, templateSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteTemplates(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TEMPLATE);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
}
