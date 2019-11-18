package com.app.ecom.store.userservice.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.app.ecom.store.userservice.dto.PrivilegeDto;
import com.app.ecom.store.userservice.dto.PrivilegeDtos;
import com.app.ecom.store.userservice.dto.PrivilegeSearchRequest;
import com.app.ecom.store.userservice.dto.RoleDtos;
import com.app.ecom.store.userservice.dto.WhereClause;
import com.app.ecom.store.userservice.enums.OperationType;
import com.app.ecom.store.userservice.model.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class PrivilegeMapper {

	@Autowired
	private RoleMapper roleMapper;

	public List<Privilege> privilegeDtosToPrivileges(List<PrivilegeDto> privilegeDtos) {
		if (CollectionUtils.isEmpty(privilegeDtos)) {
			return Collections.emptyList();
		}

		List<Privilege> privileges = new ArrayList<>();
		privilegeDtos.stream().forEach(privilegeDto -> privileges.add(privilegeDtoToPrivilege(privilegeDto)));
		return privileges;
	}

	public Privilege privilegeDtoToPrivilege(PrivilegeDto privilegeDto) {
		if (null == privilegeDto) {
			return null;
		}

		Privilege privilege = new Privilege();
		privilege.setId(privilegeDto.getId());
		privilege.setName(privilegeDto.getName());
		privilege.setDescription(privilegeDto.getDescription());
		privilege.setParentId(privilegeDto.getParentId());
		return privilege;
	}
	
	public PrivilegeDtos privilegesToPrivilegeDtos(List<Privilege> privileges) {
		return privilegesToPrivilegeDtos(privileges, false);
	}

	public PrivilegeDtos privilegesToPrivilegeDtos(List<Privilege> privileges, boolean isRoleRequired) {
		PrivilegeDtos privilegeDtos = new PrivilegeDtos();
		if (CollectionUtils.isEmpty(privileges)) {
			privilegeDtos.setPrivileges(Collections.emptyList());
		} else {
			List<PrivilegeDto> listOfPrivilegeDto = new ArrayList<>();
			privileges.stream().forEach(privilege -> listOfPrivilegeDto.add(privilegeToPrivilegeDto(privilege, isRoleRequired)));
			privilegeDtos.setPrivileges(listOfPrivilegeDto);
		}
		return privilegeDtos;
	}

	public PrivilegeDto privilegeToPrivilegeDto(Privilege privilege) {
		return privilegeToPrivilegeDto(privilege, false);
	}

	public PrivilegeDto privilegeToPrivilegeDto(Privilege privilege, boolean isRoleRequired) {
		if (null == privilege) {
			return null;
		}

		PrivilegeDto privilegeDto = new PrivilegeDto();
		privilegeDto.setId(privilege.getId());
		privilegeDto.setName(privilege.getName());
		privilegeDto.setDescription(privilege.getDescription());
		privilegeDto.setParentId(privilege.getParentId());
		if (isRoleRequired) {
			RoleDtos roleDtos = roleMapper.rolesToRoleDtos(privilege.getRoles());
			privilegeDto.setRoleDtos(roleDtos == null ? null : roleDtos.getRoles());
		}
		return privilegeDto;
	}

	public List<WhereClause> privilegeSearchRequestToWhereClauses(PrivilegeSearchRequest privilegeSearchRequest) {
		return getWhereClauses(privilegeSearchRequest.getIds(), privilegeSearchRequest.getName());
	}

	private List<WhereClause> getWhereClauses(List<Long> ids, String name) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (!CollectionUtils.isEmpty(ids)) {
			WhereClause whereClause = new WhereClause("id", (ids.size()>1 ? ids : ids.get(0)), (ids.size() > 1 ? OperationType.IN : OperationType.EQUALS));
			whereClauses.add(whereClause);
		} else {
			if (!StringUtils.isEmpty(name)) {
				WhereClause whereClause = new WhereClause("name", name, OperationType.LIKE);
				whereClauses.add(whereClause);
			}
		}
		return whereClauses;
	}
}
