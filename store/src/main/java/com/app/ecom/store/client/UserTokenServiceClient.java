package com.app.ecom.store.client;

import java.util.HashMap;
import java.util.Map;

import com.app.ecom.store.dto.ExternalApi;
import com.app.ecom.store.dto.usertokenservice.UserTokenDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserTokenServiceClient {
	
	private static final Logger logger = LogManager.getLogger(UserTokenServiceClient.class);
	
	private static final String TOKEN = "/token";
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Value("${base-urls.user-token-service-api}")
	private String userTokenServiceApiBaseUrl;
	
	public UserTokenDto createToken(UserTokenDto userTokenDto) {
		String url = new StringBuilder(userTokenServiceApiBaseUrl).append(TOKEN).toString();
		ExternalApi<UserTokenDto> externalApi = getExternalApi(UserTokenDto.class, url, HttpMethod.PUT, null, null, userTokenDto);
		ResponseEntity<UserTokenDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to PUT API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public UserTokenDto getToken(String token) {
		String url = new StringBuilder(userTokenServiceApiBaseUrl).append(TOKEN).toString();
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put("token", token);
		ExternalApi<UserTokenDto> externalApi = getExternalApi(UserTokenDto.class, url, HttpMethod.GET, null, queryParamMap, null);
		ResponseEntity<UserTokenDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to GET API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	private <T> ExternalApi<T> getExternalApi(Class<T> type, String url, HttpMethod method, Map<String, String> headers, Map<String, String> parameterMap, Object body) {
		ExternalApi<T> externalApi = new ExternalApi<>();
		externalApi.setType(type);
		externalApi.setUrl(url);
		externalApi.setMethod(method);
		externalApi.setHeaders(headers);
		externalApi.setParameterMap(parameterMap);
		externalApi.setBody(body);
		return externalApi;
	}

}
