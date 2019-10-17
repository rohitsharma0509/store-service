package com.app.ecom.store.masterdata.service;

import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.privilege.PrivilegeDto;
import com.app.ecom.store.masterdata.dto.privilege.PrivilegeDtos;
import com.app.ecom.store.masterdata.dto.privilege.PrivilegeSearchRequest;

public interface PrivilegeService {
	
	PrivilegeDto addUpdatePrivilege(PrivilegeDto privilegeDto);
	
	Long countPrivileges(PrivilegeDto privilegeDto);

	PrivilegeDtos getPrivileges(PrivilegeSearchRequest privilegeDtoSearchRequest);
	
	void deletePrivileges(IdsDto idsDto);

	/*List<PrivilegeDto> getPrivileges();
	
	PrivilegeDto getPrivilegeById(Long id);
	
	PrivilegeDto addPrivilege(PrivilegeDto privilegeDto);
	
	List<PrivilegeDto> getPrivilegesByIdIn(List<Long> ids);

	boolean deletePrivilegeById(Long id);

	boolean deletePrivileges(List<Long> ids);
	
	List<PrivilegeDto> getAllPrivileges();

	boolean deleteAllPrivileges();

	boolean isRoleAssociatedWithPrivileges(List<Long> privilegeIds);*/

}
