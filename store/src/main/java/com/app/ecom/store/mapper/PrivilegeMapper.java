package com.app.ecom.store.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.app.ecom.store.dto.PrivilegeDto;
import com.app.ecom.store.model.Privilege;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class PrivilegeMapper {
	public List<Privilege> privilegeDtosToPrivileges(List<PrivilegeDto> privilegeDtos) {
		if (CollectionUtils.isEmpty(privilegeDtos)) {
			return Collections.emptyList();
		}

		List<Privilege> privileges = new ArrayList<>();
		privilegeDtos.stream().forEach(privilegeDto -> privileges.add(privilegeDtoToPrivilege(privilegeDto)));
		return privileges;
	}

	public Privilege privilegeDtoToPrivilege(PrivilegeDto privilegeDto) {
		if(null == privilegeDto) {
			return null;
		}
		
		Privilege privilege = new Privilege();
		privilege.setId(privilegeDto.getId());
		privilege.setName(privilegeDto.getName());
		privilege.setDescription(privilegeDto.getDescription());
		privilege.setParentId(privilegeDto.getParentId());
		return privilege;
	}

	public List<PrivilegeDto> privilegesToPrivilegeDtos(List<Privilege> privileges) {
		if (CollectionUtils.isEmpty(privileges)) {
			return Collections.emptyList();
		}
		
		List<PrivilegeDto> privilegeDtos = new ArrayList<>();
		privileges.stream().forEach(privilege -> privilegeDtos.add(privilegeToPrivilegeDto(privilege)));
		return privilegeDtos;
	}

	public PrivilegeDto privilegeToPrivilegeDto(Privilege privilege) {
		if(null == privilege) {
			return null;
		}
		
		PrivilegeDto privilegeDto = new PrivilegeDto();
		privilegeDto.setId(privilege.getId());
		privilegeDto.setName(privilege.getName());
		privilegeDto.setDescription(privilege.getDescription());
		privilegeDto.setParentId(privilege.getParentId());
		return privilegeDto;
	}
}
