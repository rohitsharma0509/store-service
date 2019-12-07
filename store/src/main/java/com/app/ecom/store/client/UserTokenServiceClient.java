package com.app.ecom.store.client;

import java.util.HashMap;
import java.util.Map;

import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.usertokenservice.UserTokenDto;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class UserTokenServiceClient {
	
	private static final String TOKEN = "/token";
	
	@Value("${application.order-service.name}")
	private String serviceName;
	
	@Value("${application.order-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public UserTokenDto createToken(UserTokenDto userTokenDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TOKEN);
		ExternalApiRequest<UserTokenDto> externalApi = commonUtil.getExternalApiRequest(UserTokenDto.class, url, HttpMethod.PUT, null, null, userTokenDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public UserTokenDto getToken(String token) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, TOKEN);
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put("token", token);
		ExternalApiRequest<UserTokenDto> externalApi = commonUtil.getExternalApiRequest(UserTokenDto.class, url, HttpMethod.GET, null, queryParamMap, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
}
