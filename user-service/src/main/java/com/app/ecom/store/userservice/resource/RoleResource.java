package com.app.ecom.store.userservice.resource;

import com.app.ecom.store.userservice.constants.Endpoint;
import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.RoleDto;
import com.app.ecom.store.userservice.dto.RoleDtos;
import com.app.ecom.store.userservice.dto.RoleSearchRequest;
import com.app.ecom.store.userservice.service.RoleService;
import com.app.ecom.store.userservice.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleResource {
	
	private static final Logger logger = LogManager.getLogger(RoleResource.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.ROLE)
	public ResponseEntity<RoleDto> addUpdateRole(@RequestBody RoleDto roleDto) {
		try {
			RoleDto createdRoleDto = roleService.addUpdateRole(roleDto);
			return new ResponseEntity<>(createdRoleDto, roleDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while adding role: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = Endpoint.GET_ROLE)
	public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
		try {
			RoleDto roleDto = roleService.getRoleById(id);
			return new ResponseEntity<>(roleDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting role: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.ROLE)
	public ResponseEntity<RoleDtos> getRoles(@RequestBody RoleSearchRequest roleSearchRequest) {
		try {
			RoleDtos roleDtos = roleService.getRoles(roleSearchRequest);
			return new ResponseEntity<>(roleDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting roles: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_ROLE)
	public ResponseEntity<Long> countRoles(@RequestBody RoleSearchRequest roleSearchRequest) {
		try {
			Long noOfRoles = roleService.countRoles(roleSearchRequest);
			return new ResponseEntity<>(noOfRoles, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting role: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.ROLE)
	public ResponseEntity<Void> deleteRoles(@RequestBody IdsDto idsDto) {
		try {
			roleService.deleteRoles(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting role: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
