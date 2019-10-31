package com.app.ecom.store.service;

import java.util.List;
import java.util.Map;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.userservice.PrivilegeDto;
import org.springframework.data.domain.Pageable;

public interface PrivilegeService {

	PrivilegeDto getPrivilegeById(Long id);
	
	PrivilegeDto addPrivilege(PrivilegeDto privilegeDto);
	
	CustomPage<PrivilegeDto> getPrivileges(Pageable pageable, Map<String, String> params);
	
	List<PrivilegeDto> getPrivilegesByIdIn(List<Long> ids);

	List<PrivilegeDto> getAllPrivileges();

	void deletePrivileges(IdsDto idsDto);

	boolean isRoleAssociatedWithPrivileges(List<Long> privilegeIds);

}
