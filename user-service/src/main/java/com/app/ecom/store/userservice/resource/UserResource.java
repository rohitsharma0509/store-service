package com.app.ecom.store.userservice.resource;

import com.app.ecom.store.userservice.constants.Endpoint;
import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.UserDto;
import com.app.ecom.store.userservice.dto.UserDtos;
import com.app.ecom.store.userservice.dto.UserSearchRequest;
import com.app.ecom.store.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PutMapping(value = Endpoint.USER)
	public ResponseEntity<UserDto> createUpdateUser(@RequestBody UserDto userDto) {
		try {
			UserDto createdUserDto = userService.createUpdateUser(userDto);
			return new ResponseEntity<>(createdUserDto, userDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.USER)
	public ResponseEntity<UserDtos> getUsers(@RequestBody UserSearchRequest userSearchRequest) {
		try {
			UserDtos productDtos = userService.getUsers(userSearchRequest);
			return new ResponseEntity<>(productDtos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_USER)
	public ResponseEntity<Long> countUsers(@RequestBody UserSearchRequest userSearchRequest) {
		try {
			Long noOfUsers = userService.countUsers(userSearchRequest);
			return new ResponseEntity<>(noOfUsers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.USER)
	public ResponseEntity<Void> deleteUsers(@RequestBody IdsDto idsDto) {
		try {
			userService.deleteUsers(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}