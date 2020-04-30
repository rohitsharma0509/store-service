package com.app.ecom.store.client;

import java.util.HashMap;
import java.util.Map;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.userservice.PrivilegeDto;
import com.app.ecom.store.dto.userservice.PrivilegeDtos;
import com.app.ecom.store.dto.userservice.PrivilegeSearchRequest;
import com.app.ecom.store.dto.userservice.RoleDto;
import com.app.ecom.store.dto.userservice.RoleDtos;
import com.app.ecom.store.dto.userservice.RoleSearchRequest;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.dto.userservice.UserDtos;
import com.app.ecom.store.dto.userservice.UserSearchRequest;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClient {
	
	private static final String USER = "/user";
	private static final String COUNT_USER = "/countUser";
	private static final String ROLE = "/role";
	private static final String COUNT_ROLE = "/countRole";
	private static final String PRIVILEGE = "/privilege";
	private static final String COUNT_PRIVILEGE = "/countPrivilege";
	
	@Value("${application.user-service.name}")
	private String serviceName;
	
	@Value("${application.user-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public UserDto createUpdateUser(UserDto userDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, USER);
		ExternalApiRequest<UserDto> externalApi = commonUtil.getExternalApiRequest(UserDto.class, url, HttpMethod.PUT, null, null, userDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public UserDto getUser(Long id) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, USER);
		ExternalApiRequest<UserDto> externalApi = commonUtil.getExternalApiRequest(UserDto.class, url+"/"+id, HttpMethod.GET, null, null, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public UserDtos getUsers(UserSearchRequest userSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, USER);
		ExternalApiRequest<UserDtos> externalApi = commonUtil.getExternalApiRequest(UserDtos.class, url, HttpMethod.POST, null, null, userSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countUsers(UserSearchRequest userSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_USER);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, userSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteUsers(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, USER);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
	
	public boolean changePassword(Long userId, String pswrd) {
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put(FieldNames.PSWRD, pswrd);
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, USER);
		ExternalApiRequest<Boolean> externalApi = commonUtil.getExternalApiRequest(Boolean.class, url+ "/" +userId, HttpMethod.PUT, null, queryParamMap, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public RoleDto createUpdateRole(RoleDto roleDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, ROLE);
		ExternalApiRequest<RoleDto> externalApi = commonUtil.getExternalApiRequest(RoleDto.class, url, HttpMethod.PUT, null, null, roleDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public RoleDtos getRoles(RoleSearchRequest roleSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, ROLE);
		ExternalApiRequest<RoleDtos> externalApi = commonUtil.getExternalApiRequest(RoleDtos.class, url, HttpMethod.POST, null, null, roleSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countRoles(RoleSearchRequest roleSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_ROLE);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, roleSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteRoles(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, ROLE);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
	
	public PrivilegeDto createUpdatePrivilege(PrivilegeDto privilegeDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, PRIVILEGE);
		ExternalApiRequest<PrivilegeDto> externalApi = commonUtil.getExternalApiRequest(PrivilegeDto.class, url, HttpMethod.PUT, null, null, privilegeDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public PrivilegeDtos getPrivileges(PrivilegeSearchRequest privilegeSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, PRIVILEGE);
		ExternalApiRequest<PrivilegeDtos> externalApi = commonUtil.getExternalApiRequest(PrivilegeDtos.class, url, HttpMethod.POST, null, null, privilegeSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countPrivileges(PrivilegeSearchRequest privilegeSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_PRIVILEGE);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, privilegeSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deletePrivileges(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, PRIVILEGE);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
}
