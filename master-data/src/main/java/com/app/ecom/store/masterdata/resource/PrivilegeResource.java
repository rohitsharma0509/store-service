/*package com.app.ecom.store.masterdata.resource;

import com.app.ecom.store.masterdata.constants.Endpoint;
import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.privilege.PrivilegeDto;
import com.app.ecom.store.masterdata.dto.privilege.PrivilegeDtos;
import com.app.ecom.store.masterdata.dto.privilege.PrivilegeSearchRequest;
import com.app.ecom.store.masterdata.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivilegeResource {
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@PutMapping(value = Endpoint.PRIVILEGE)
	public ResponseEntity<PrivilegeDto> addUpdatePrivilege(@RequestBody PrivilegeDto privilegeDto) {
		try {
			PrivilegeDto createdPrivilegeDto = privilegeService.addUpdatePrivilege(privilegeDto);
			return new ResponseEntity<>(createdPrivilegeDto, privilegeDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.PRIVILEGE)
	public ResponseEntity<PrivilegeDtos> getAllPrivileges(@RequestBody PrivilegeSearchRequest privilegeSearchRequest) {
		try {
			PrivilegeDtos privilegeDtos = privilegeService.getPrivileges(privilegeSearchRequest);
			return new ResponseEntity<>(privilegeDtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_PRIVILEGE)
	public ResponseEntity<Long> countPrivileges(@RequestBody PrivilegeDto privilegeDto) {
		try {
			Long noOfPrivileges = privilegeService.countPrivileges(privilegeDto);
			return new ResponseEntity<>(noOfPrivileges, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.PRIVILEGE)
	public ResponseEntity<Void> deletePrivileges(@RequestBody IdsDto idsDto) {
		try {
			privilegeService.deletePrivileges(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
*/