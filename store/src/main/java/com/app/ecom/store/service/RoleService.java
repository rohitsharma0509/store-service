package com.app.ecom.store.service;

import java.util.List;
import java.util.Map;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.userservice.RoleDto;
import org.springframework.data.domain.Pageable;

public interface RoleService {
	CustomPage<RoleDto> getRoles(Pageable pageable, Map<String, String> params);
	
	List<RoleDto> getRolesByIdIn(List<Long> ids);

	RoleDto addRole(RoleDto roleDto);
	
	RoleDto getRoleByName(String name);

	RoleDto getRoleById(Long id);

	void deleteRoles(IdsDto idsDto);
	
	List<RoleDto> getAllRoles();
}
