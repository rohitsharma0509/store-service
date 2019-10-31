package com.app.ecom.store.events;

import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.dto.userservice.PrivilegeDto;
import com.app.ecom.store.dto.userservice.RoleDto;
import com.app.ecom.store.dto.userservice.UserDto;
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

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!isDone) {
			UserDto user = userService.findUserByUsername("rohits");
			if (null == user) {
				UserDto userDto = new UserDto();
				userDto.setFirstName("Rohit");
				userDto.setLastName("Sharma");
				userDto.setUsername("rohits");
				userDto.setPassword("Admin@123");
				userDto.setIsEnabled(true);
				userDto.setEmail("rohitsharm0509@gmail.com");
				userDto.setLanguage("en");
				userDto.setMobile("8860254047");
				userDto.setRoles(getAdminRoleDto());
				userService.createUser(userDto);
				isDone = true;
			}
		}
	}

	private List<RoleDto> getAdminRoleDto() {
		List<RoleDto> roleDtos = new ArrayList<>();
		RoleDto roleDto = new RoleDto();
		roleDto.setName("ROLE_ADMIN");
		roleDto.setPrivilegeDtos(getAdminPrivilegeDto());
		roleDtos.add(roleDto);
		return roleDtos;
	}

	private List<PrivilegeDto> getAdminPrivilegeDto() {
		List<PrivilegeDto> privilegeDtos = new ArrayList<>();
		PrivilegeDto privilegeDto = new PrivilegeDto();
		privilegeDto.setName("ADMIN");
		privilegeDto.setDescription("Is Admin?");
		privilegeDtos.add(privilegeDto);
		return privilegeDtos;
	}
}