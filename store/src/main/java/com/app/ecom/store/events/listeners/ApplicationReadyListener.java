package com.app.ecom.store.events.listeners;

import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.userservice.PrivilegeDto;
import com.app.ecom.store.dto.userservice.RoleDto;
import com.app.ecom.store.enums.RoleType;
import com.app.ecom.store.service.PrivilegeService;
import com.app.ecom.store.service.RoleService;
import com.app.ecom.store.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
	
	private static final Logger logger = LogManager.getLogger(ApplicationReadyListener.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PrivilegeService privilegeService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		/*try {
			UserDto user = userService.findUserByUsername("rohits");
			if (null == user) {
				//saveRole(Constants.DEFAULT_GUEST_ROLE, Constants.DEFAULT_GUEST_PRIVILEGE, Constants.DEFAULT_GUEST_PRIVILEGE_DESC, RoleType.GUEST);
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
			}
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while pushing default users and roles: ").append(e.getMessage()));
		}*/
	}

	private List<RoleDto> getAdminRole() {
		List<RoleDto> roleDtos = new ArrayList<>();
		roleDtos.add(saveRole(Constants.DEFAULT_ADMIN_ROLE, Constants.DEFAULT_ADMIN_PRIVILEGE, Constants.DEFAULT_ADMIN_PRIVILEGE_DESC, RoleType.ADMIN));
		return roleDtos;
	}
	
	private RoleDto saveRole(String roleName, String privilegeName, String privilegeDesc, RoleType type) {
		RoleDto roleDto = new RoleDto();
		roleDto.setName(roleName);
		roleDto.setIsDeletable(false);
		roleDto.setType(type);
		roleDto.setPrivilegeDtos(getPrivileges(privilegeName, privilegeDesc, type));
		return roleService.addRole(roleDto);
	}
	
	private List<PrivilegeDto> getPrivileges(String privilegeName, String privilegeDesc, RoleType type) {
		List<PrivilegeDto> privilegeDtos = new ArrayList<>();
		PrivilegeDto privilegeDto = savePrivilege(privilegeName, privilegeDesc, type);
		privilegeDto.setIsInRole(true);
		privilegeDtos.add(privilegeDto);
		return privilegeDtos;
	}
	
	private PrivilegeDto savePrivilege(String name, String desc, RoleType type) {
		PrivilegeDto privilegeDto = new PrivilegeDto();
		privilegeDto.setName(name);
		privilegeDto.setDescription(desc);
		privilegeDto.setType(type);
		return privilegeService.addPrivilege(privilegeDto);
	}
}