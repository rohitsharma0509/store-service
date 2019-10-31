package com.app.ecom.store.client;

import java.util.Map;

import com.app.ecom.store.dto.ExternalApi;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClient {
	
	private static final Logger logger = LogManager.getLogger(UserServiceClient.class);
	
	private static final String USER = "/user";
	private static final String COUNT_USER = "/countUser";
	private static final String ROLE = "/role";
	private static final String COUNT_ROLE = "/countRole";
	private static final String PRIVILEGE = "/privilege";
	private static final String COUNT_PRIVILEGE = "/countPrivilege";
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Value("${base-urls.user-service-api}")
	private String userServiceApiBaseUrl;
	
	public UserDto createUpdateUser(UserDto userDto) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(USER).toString();
		ExternalApi<UserDto> externalApi = getExternalApi(UserDto.class, url, HttpMethod.PUT, null, null, userDto);
		ResponseEntity<UserDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to PUT API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public UserDtos getUsers(UserSearchRequest userSearchRequest) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(USER).toString();
		ExternalApi<UserDtos> externalApi = getExternalApi(UserDtos.class, url, HttpMethod.POST, null, null, userSearchRequest);
		ResponseEntity<UserDtos> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public Long countUsers(UserSearchRequest userSearchRequest) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(COUNT_USER).toString();
		ExternalApi<Long> externalApi = getExternalApi(Long.class, url, HttpMethod.POST, null, null, userSearchRequest);
		ResponseEntity<Long> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public void deleteUsers(IdsDto idsDto) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(USER).toString();
		ExternalApi<Void> externalApi = getExternalApi(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		ResponseEntity<Void> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.info(new StringBuilder("Users has been deleted successfully.").toString());
		} else {
			logger.error(new StringBuilder("Call to DElETE API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
		}
	}
	
	public RoleDto createUpdateRole(RoleDto roleDto) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(ROLE).toString();
		ExternalApi<RoleDto> externalApi = getExternalApi(RoleDto.class, url, HttpMethod.PUT, null, null, roleDto);
		ResponseEntity<RoleDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to PUT API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public RoleDtos getRoles(RoleSearchRequest roleSearchRequest) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(ROLE).toString();
		ExternalApi<RoleDtos> externalApi = getExternalApi(RoleDtos.class, url, HttpMethod.POST, null, null, roleSearchRequest);
		ResponseEntity<RoleDtos> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public Long countRoles(RoleSearchRequest roleSearchRequest) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(COUNT_ROLE).toString();
		ExternalApi<Long> externalApi = getExternalApi(Long.class, url, HttpMethod.POST, null, null, roleSearchRequest);
		ResponseEntity<Long> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public void deleteRoles(IdsDto idsDto) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(ROLE).toString();
		ExternalApi<Void> externalApi = getExternalApi(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		ResponseEntity<Void> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.info(new StringBuilder("Roles has been deleted successfully.").toString());
		} else {
			logger.error(new StringBuilder("Call to DElETE API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
		}
	}
	
	public PrivilegeDto createUpdatePrivilege(PrivilegeDto privilegeDto) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(PRIVILEGE).toString();
		ExternalApi<PrivilegeDto> externalApi = getExternalApi(PrivilegeDto.class, url, HttpMethod.PUT, null, null, privilegeDto);
		ResponseEntity<PrivilegeDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to PUT API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public PrivilegeDtos getPrivileges(PrivilegeSearchRequest privilegeSearchRequest) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(PRIVILEGE).toString();
		ExternalApi<PrivilegeDtos> externalApi = getExternalApi(PrivilegeDtos.class, url, HttpMethod.POST, null, null, privilegeSearchRequest);
		ResponseEntity<PrivilegeDtos> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public Long countPrivileges(PrivilegeSearchRequest privilegeSearchRequest) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(COUNT_PRIVILEGE).toString();
		ExternalApi<Long> externalApi = getExternalApi(Long.class, url, HttpMethod.POST, null, null, privilegeSearchRequest);
		ResponseEntity<Long> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public void deletePrivileges(IdsDto idsDto) {
		String url = new StringBuilder(userServiceApiBaseUrl).append(PRIVILEGE).toString();
		ExternalApi<Void> externalApi = getExternalApi(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		ResponseEntity<Void> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.info(new StringBuilder("Privileges has been deleted successfully.").toString());
		} else {
			logger.error(new StringBuilder("Call to DElETE API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
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
