package com.app.ecom.store.userservice.service;

import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.PrivilegeDto;
import com.app.ecom.store.userservice.dto.PrivilegeDtos;
import com.app.ecom.store.userservice.dto.PrivilegeSearchRequest;

public interface PrivilegeService {
	
	PrivilegeDto addUpdatePrivilege(PrivilegeDto privilegeDto);
	
	Long countPrivileges(PrivilegeSearchRequest privilegeSearchRequest);

	PrivilegeDtos getPrivileges(PrivilegeSearchRequest privilegeDtoSearchRequest);
	
	void deletePrivileges(IdsDto idsDto);
}
