package com.app.ecom.store.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.app.ecom.store.dto.RoleDto;
import com.app.ecom.store.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RoleMapper {

	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Autowired
	private UserMapper userMapper;

	public List<Role> roleDtosToRoles(List<RoleDto> roleDtos) {
		if (CollectionUtils.isEmpty(roleDtos)) {
			return Collections.emptyList();
		}

		List<Role> roles = new ArrayList<>();
		roleDtos.stream().forEach(roleDto -> roles.add(roleDtoToRole(roleDto)));
		return roles;
	}

	public Role roleDtoToRole(RoleDto roleDto) {
		if(null == roleDto) {
			return null;
		}
		
		Role role = new Role();
		role.setId(roleDto.getId());
		role.setName(roleDto.getName());
		role.setDescription(roleDto.getDescription());
		role.setPrivileges(privilegeMapper.privilegeDtosToPrivileges(roleDto.getPrivilegeDtos()));
		return role;
	}
	
	public Set<RoleDto> rolesToRoleDtos(Set<Role> roles) {
		if (CollectionUtils.isEmpty(roles)) {
			return Collections.emptySet();
		}

		Set<RoleDto> roleDtos = new HashSet<>();
		roles.stream().forEach(role -> roleDtos.add(roleToRoleDto(role)));
		return roleDtos;
	}

	public RoleDto roleToRoleDto(Role role) {
		if(null == role) {
			return null;
		}
		
		RoleDto roleDto = new RoleDto();
		roleDto.setId(role.getId());
		roleDto.setName(role.getName());
		roleDto.setOldName(role.getName());
		roleDto.setDescription(role.getDescription());
		roleDto.setPrivilegeDtos(privilegeMapper.privilegesToPrivilegeDtos(role.getPrivileges()));
		roleDto.setUserDtos(userMapper.usersToUserDtos(role.getUsers()));
		return roleDto;
	}
}
