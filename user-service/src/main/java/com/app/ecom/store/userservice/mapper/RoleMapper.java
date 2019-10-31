package com.app.ecom.store.userservice.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.app.ecom.store.userservice.dto.PrivilegeDtos;
import com.app.ecom.store.userservice.dto.RoleDto;
import com.app.ecom.store.userservice.dto.RoleDtos;
import com.app.ecom.store.userservice.dto.RoleSearchRequest;
import com.app.ecom.store.userservice.dto.UserDtos;
import com.app.ecom.store.userservice.dto.WhereClause;
import com.app.ecom.store.userservice.enums.OperationType;
import com.app.ecom.store.userservice.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class RoleMapper {
	
	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public List<Role> roleDtosToRoles(RoleDtos roleDtos) {
		if (roleDtos ==null || CollectionUtils.isEmpty(roleDtos.getRoles())) {
			return Collections.emptyList();
		}

		List<Role> roles = new ArrayList<>();
		roleDtos.getRoles().stream().forEach(roleDto -> roles.add(roleDtoToRole(roleDto)));
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
	
	public RoleDtos rolesToRoleDtos(List<Role> roles) {
		RoleDtos roleDtos = new RoleDtos();
		if (CollectionUtils.isEmpty(roles)) {
			roleDtos.setRoles(Collections.emptyList());
		} else {			
			List<RoleDto> listOfRoleDtos = new ArrayList<>();
			roles.stream().forEach(role -> listOfRoleDtos.add(roleToRoleDto(role)));
			roleDtos.setRoles(listOfRoleDtos);
		}

		return roleDtos;
	}

	public RoleDto roleToRoleDto(Role role) {
		return roleToRoleDto(role, false);
	}
	
	public RoleDto roleToRoleDto(Role role, Boolean isUserRequired) {
		if(null == role) {
			return null;
		}
		
		RoleDto roleDto = new RoleDto();
		roleDto.setId(role.getId());
		roleDto.setName(role.getName());
		roleDto.setOldName(role.getName());
		roleDto.setDescription(role.getDescription());
		PrivilegeDtos privilegeDtos = privilegeMapper.privilegesToPrivilegeDtos(role.getPrivileges());
		roleDto.setPrivilegeDtos(privilegeDtos==null ? null : privilegeDtos.getPrivileges());
		if(isUserRequired) {
			UserDtos userDtos = userMapper.usersToUserDtos(role.getUsers());
			roleDto.setUserDtos(userDtos == null ? null : userDtos.getUsers());
		}
		return roleDto;
	}

	public List<WhereClause> roleSearchRequestToWhereClauses(RoleSearchRequest roleSearchRequest) {
		return getWhereClauses(roleSearchRequest.getRoleIds(), roleSearchRequest.getRoleName(), roleSearchRequest.getUserName());
	}

	private List<WhereClause> getWhereClauses(List<Long> roleIds, String roleName, String userName) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (!CollectionUtils.isEmpty(roleIds)) {
			WhereClause whereClause = new WhereClause("id", (roleIds.size()>1 ? roleIds : roleIds.get(0)), (roleIds.size() > 1 ? OperationType.IN : OperationType.EQUALS));
			whereClauses.add(whereClause);
		} else {
			if (!StringUtils.isEmpty(roleName)) {
				WhereClause whereClause = new WhereClause("name", roleName, OperationType.LIKE);
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(userName)) {
				
			}
		}
		return whereClauses;
	}
}
