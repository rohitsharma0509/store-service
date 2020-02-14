package com.app.ecom.store.userservice.service.impl;

import java.util.List;
import java.util.Optional;

import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.QueryRequest;
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
import org.springframework.util.CollectionUtils;

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
		if(userDto.getId() != null) {
			Optional<User> optionalUser = userRepository.findById(userDto.getId());
	    	if(optionalUser.isPresent()) {
	    		User userToUpdate = optionalUser.get();
	    		userToUpdate.setFirstName(userDto.getFirstName());
	    		userToUpdate.setLastName(userDto.getLastName());
	    		userToUpdate.setEmail(userDto.getEmail());
	    		userToUpdate.setMobile(userDto.getMobile());
	    		userToUpdate.setLanguage(userDto.getLanguage());
	    		return userMapper.userToUserDto(userRepository.save(userToUpdate));
	    	} else {
	    		return null;
	    	}
		} else {
			return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDto)));
		}
	}
	
	@Override
	public UserDto getUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.isPresent() ? userMapper.userToUserDto(optionalUser.get()) : null;
	}

	@Override
	public UserDtos getUsers(UserSearchRequest userSearchRequest) {
		queryHandler.setType(User.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(userMapper.userSearchRequestToWhereClauses(userSearchRequest));
		queryRequest.setOrderByClauses(userSearchRequest.getOrderByClauses());
		queryRequest.setOffset(userSearchRequest.getOffset());
		queryRequest.setLimit(userSearchRequest.getLimit());
		List<User> users = queryHandler.findByQueryRequest(queryRequest);
		return userMapper.usersToUserDtos(users);
	}

	@Override
	public Long countUsers(UserSearchRequest userSearchRequest) {
		queryHandler.setType(User.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(userMapper.userSearchRequestToWhereClauses(userSearchRequest));
		return queryHandler.countByQueryRequest(queryRequest, "id");
	}

	@Override
	public void deleteUsers(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			userRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			userRepository.deleteById(idsDto.getIds().get(0));
		} else {
			userRepository.deleteByIdIn(idsDto.getIds());
		}
	}
}
