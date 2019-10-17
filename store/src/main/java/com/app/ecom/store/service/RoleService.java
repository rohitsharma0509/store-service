package com.app.ecom.store.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.RoleDto;
import org.springframework.data.domain.Pageable;

public interface RoleService {
	CustomPage<RoleDto> getRoles(Pageable pageable, Map<String, String> params);
	
	Set<RoleDto> getRolesByIdIn(List<Long> ids);

	RoleDto addRole(RoleDto roleDto);
	
	RoleDto getRoleByName(String name);

	RoleDto getRoleById(Long id);

	boolean deleteRoleById(Long id);

	boolean deleteRoles(List<Long> ids);
	
	Set<RoleDto> getAllRoles();

	boolean deleteAllRoles();
}
