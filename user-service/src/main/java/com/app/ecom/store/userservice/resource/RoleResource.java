package com.app.ecom.store.userservice.resource;

import com.app.ecom.store.userservice.constants.Endpoint;
import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.RoleDto;
import com.app.ecom.store.userservice.dto.RoleDtos;
import com.app.ecom.store.userservice.dto.RoleSearchRequest;
import com.app.ecom.store.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleResource {
	
	@Autowired
	private RoleService roleService;
	
	@PutMapping(value = Endpoint.ROLE)
	public ResponseEntity<RoleDto> addUpdateRole(@RequestBody RoleDto roleDto) {
		try {
			RoleDto createdRoleDto = roleService.addUpdateRole(roleDto);
			return new ResponseEntity<>(createdRoleDto, roleDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.ROLE)
	public ResponseEntity<RoleDtos> getAllRoles(@RequestBody RoleSearchRequest roleSearchRequest) {
		try {
			RoleDtos roleDtos = roleService.getRoles(roleSearchRequest);
			return new ResponseEntity<>(roleDtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_ROLE)
	public ResponseEntity<Long> countRoles(@RequestBody RoleSearchRequest roleSearchRequest) {
		try {
			Long noOfRoles = roleService.countRoles(roleSearchRequest);
			return new ResponseEntity<>(noOfRoles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.ROLE)
	public ResponseEntity<Void> deleteRoles(@RequestBody IdsDto idsDto) {
		try {
			roleService.deleteRoles(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
