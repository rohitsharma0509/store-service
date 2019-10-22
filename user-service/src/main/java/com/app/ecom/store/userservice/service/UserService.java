package com.app.ecom.store.userservice.service;

import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.UserDto;
import com.app.ecom.store.userservice.dto.UserDtos;
import com.app.ecom.store.userservice.dto.UserSearchRequest;

public interface UserService {

	UserDto createUpdateUser(UserDto userDto);

	UserDtos getProducts(UserSearchRequest userSearchRequest);

	Long countUsers(UserSearchRequest userSearchRequest);

	void deleteUsers(IdsDto idsDto);

	
}
