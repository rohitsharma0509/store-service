package com.app.ecom.store.service;

import java.util.List;
import java.util.Map;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.PrivilegeDto;
import org.springframework.data.domain.Pageable;

public interface PrivilegeService {

	List<PrivilegeDto> getPrivileges();
	
	PrivilegeDto getPrivilegeById(Long id);
	
	PrivilegeDto addPrivilege(PrivilegeDto privilegeDto);
	
	CustomPage<PrivilegeDto> getPrivileges(Pageable pageable, Map<String, String> params);
	
	List<PrivilegeDto> getPrivilegesByIdIn(List<Long> ids);

	boolean deletePrivilegeById(Long id);

	boolean deletePrivileges(List<Long> ids);
	
	List<PrivilegeDto> getAllPrivileges();

	boolean deleteAllPrivileges();

	boolean isRoleAssociatedWithPrivileges(List<Long> privilegeIds);

}
