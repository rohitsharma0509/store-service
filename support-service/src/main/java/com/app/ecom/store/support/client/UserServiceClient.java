package com.app.ecom.store.support.client;

import com.app.ecom.store.support.dto.ExternalApiRequest;
import com.app.ecom.store.support.dto.userservice.RoleDto;
import com.app.ecom.store.support.dto.userservice.UserDto;
import com.app.ecom.store.support.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClient {
	
	private static final String USER = "/user";
	private static final String ROLE = "/role";
	
	@Value("${application.user-service.name}")
	private String serviceName;
	
	@Value("${application.user-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public UserDto getUserByUserId(Long userId) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, USER+"/"+userId);
		ExternalApiRequest<UserDto> externalApi = commonUtil.getExternalApiRequest(UserDto.class, url, HttpMethod.GET, null, null, null);
		return externalApiHandler.callExternalApi(externalApi);
	}

	public RoleDto getRoleByRoleId(Long roleId) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, ROLE+"/"+roleId);
		ExternalApiRequest<RoleDto> externalApi = commonUtil.getExternalApiRequest(RoleDto.class, url, HttpMethod.GET, null, null, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
}
