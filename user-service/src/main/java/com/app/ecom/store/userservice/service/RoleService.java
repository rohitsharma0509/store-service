package com.app.ecom.store.userservice.service;

import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.RoleDto;
import com.app.ecom.store.userservice.dto.RoleDtos;
import com.app.ecom.store.userservice.dto.RoleSearchRequest;

public interface RoleService {

	RoleDto addUpdateRole(RoleDto roleDto);

	RoleDto getRoleById(Long id);
	
	RoleDtos getRoles(RoleSearchRequest roleSearchRequest);

	Long countRoles(RoleSearchRequest roleSearchRequest);

	void deleteRoles(IdsDto idsDto);

}
