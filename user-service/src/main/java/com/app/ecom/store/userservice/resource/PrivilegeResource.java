package com.app.ecom.store.userservice.resource;

import com.app.ecom.store.userservice.constants.Endpoint;
import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.PrivilegeDto;
import com.app.ecom.store.userservice.dto.PrivilegeDtos;
import com.app.ecom.store.userservice.dto.PrivilegeSearchRequest;
import com.app.ecom.store.userservice.service.PrivilegeService;
import com.app.ecom.store.userservice.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger logger = LogManager.getLogger(PrivilegeResource.class);
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.PRIVILEGE)
	public ResponseEntity<PrivilegeDto> addUpdatePrivilege(@RequestBody PrivilegeDto privilegeDto) {
		try {
			PrivilegeDto createdPrivilegeDto = privilegeService.addUpdatePrivilege(privilegeDto);
			return new ResponseEntity<>(createdPrivilegeDto, privilegeDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while adding privilege: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.PRIVILEGE)
	public ResponseEntity<PrivilegeDtos> getPrivileges(@RequestBody PrivilegeSearchRequest privilegeSearchRequest) {
		try {
			PrivilegeDtos privilegeDtos = privilegeService.getPrivileges(privilegeSearchRequest);
			return new ResponseEntity<>(privilegeDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting privileges: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_PRIVILEGE)
	public ResponseEntity<Long> countPrivileges(@RequestBody PrivilegeSearchRequest privilegeSearchRequest) {
		try {
			Long noOfPrivileges = privilegeService.countPrivileges(privilegeSearchRequest);
			return new ResponseEntity<>(noOfPrivileges, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting privilege: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.PRIVILEGE)
	public ResponseEntity<Void> deletePrivileges(@RequestBody IdsDto idsDto) {
		try {
			privilegeService.deletePrivileges(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting privilege: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
