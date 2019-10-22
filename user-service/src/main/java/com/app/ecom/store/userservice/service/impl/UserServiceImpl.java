package com.app.ecom.store.userservice.service.impl;

import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.UserDto;
import com.app.ecom.store.userservice.dto.UserDtos;
import com.app.ecom.store.userservice.dto.UserSearchRequest;
import com.app.ecom.store.userservice.handler.QueryHandler;
import com.app.ecom.store.userservice.mapper.UserMapper;
import com.app.ecom.store.userservice.model.User;
import com.app.ecom.store.userservice.repository.UserRepository;
import com.app.ecom.store.userservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private QueryHandler<User> queryHandler;

	@Override
	public UserDto createUpdateUser(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDtos getProducts(UserSearchRequest userSearchRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countUsers(UserSearchRequest userSearchRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUsers(IdsDto idsDto) {
		// TODO Auto-generated method stub
		
	}
}
