package com.app.ecom.store.events;

import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.userservice.PrivilegeDto;
import com.app.ecom.store.dto.userservice.RoleDto;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.service.PrivilegeService;
import com.app.ecom.store.service.RoleService;
import com.app.ecom.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
	
	boolean isDone = false;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PrivilegeService privilegeService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!isDone) {
			UserDto user = userService.findUserByUsername("rohits");
			if (null == user) {
				saveRole(Constants.DEFAULT_GUEST_ROLE, Constants.DEFAULT_GUEST_PRIVILEGE, Constants.DEFAULT_GUEST_PRIVILEGE_DESC);
				UserDto userDto = new UserDto();
				userDto.setFirstName("Rohit");
				userDto.setLastName("Sharma");
				userDto.setUsername("rohits");
				userDto.setPassword("Admin@123");
				userDto.setIsEnabled(true);
				userDto.setEmail("rohitsharm0509@gmail.com");
				userDto.setMobile("8860254047");
				userDto.setRoles(getAdminRole());
				userService.createUser(userDto);
				isDone = true;
			}
		}
	}

	private List<RoleDto> getAdminRole() {
		List<RoleDto> roleDtos = new ArrayList<>();
		roleDtos.add(saveRole(Constants.DEFAULT_ADMIN_ROLE, Constants.DEFAULT_ADMIN_PRIVILEGE, Constants.DEFAULT_ADMIN_PRIVILEGE_DESC));
		return roleDtos;
	}
	
	private RoleDto saveRole(String roleName, String privilegeName, String privilegeDesc) {
		RoleDto roleDto = new RoleDto();
		roleDto.setName(roleName);
		roleDto.setPrivilegeDtos(getPrivileges(privilegeName, privilegeDesc));
		return roleService.addRole(roleDto);
	}
	
	private List<PrivilegeDto> getPrivileges(String privilegeName, String privilegeDesc) {
		List<PrivilegeDto> privilegeDtos = new ArrayList<>();
		PrivilegeDto privilegeDto = savePrivilege(privilegeName, privilegeDesc);
		privilegeDto.setIsInRole(true);
		privilegeDtos.add(privilegeDto);
		return privilegeDtos;
	}
	
	private PrivilegeDto savePrivilege(String name, String desc) {
		PrivilegeDto privilegeDto = new PrivilegeDto();
		privilegeDto.setName(name);
		privilegeDto.setDescription(desc);
		return privilegeService.addPrivilege(privilegeDto);
	}
}