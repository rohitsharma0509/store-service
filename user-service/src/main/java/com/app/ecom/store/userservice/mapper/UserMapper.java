package com.app.ecom.store.userservice.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.app.ecom.store.userservice.dto.RoleDtos;
import com.app.ecom.store.userservice.dto.UserDto;
import com.app.ecom.store.userservice.dto.UserDtos;
import com.app.ecom.store.userservice.dto.UserSearchRequest;
import com.app.ecom.store.userservice.dto.WhereClause;
import com.app.ecom.store.userservice.enums.OperationType;
import com.app.ecom.store.userservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class UserMapper {

	private static final Logger logger = LogManager.getLogger(UserMapper.class);
	
	@Autowired
	private RoleMapper roleMapper;

	public List<User> userDtoToUser(UserDtos userDtos) {
		List<User> users = new ArrayList<>();
		if (userDtos != null && !CollectionUtils.isEmpty(userDtos.getUsers())) {
			userDtos.getUsers().stream().forEach(userDto -> users.add(userDtoToUser(userDto)));
		}
		return users;
	}

	public User userDtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		if (!StringUtils.isEmpty(userDto.getUsername())) {
			user.setUsername(userDto.getUsername());
		}
		if (!StringUtils.isEmpty(userDto.getPassword())) {
			user.setPassword(userDto.getPassword());
		}
		user.setIsEnabled((null != userDto.getIsEnabled() && userDto.getIsEnabled()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setMobile(userDto.getMobile());
		user.setLanguage(StringUtils.isEmpty(userDto.getLanguage()) ? "en" : userDto.getLanguage());
		if(!CollectionUtils.isEmpty(userDto.getRoles())) {
			RoleDtos roleDtos = new RoleDtos();
			roleDtos.setRoles(userDto.getRoles());
			user.setRoles(roleMapper.roleDtosToRoles(roleDtos));
		}
		return user;
	}

	public UserDtos usersToUserDtos(List<User> users) {
		UserDtos userDtos = new UserDtos();
		if (CollectionUtils.isEmpty(users)) {
			userDtos.setUsers(Collections.emptyList());
		} else {
			List<UserDto> listOfUserDto = new ArrayList<>();
			users.stream().forEach(user -> listOfUserDto.add(userToUserDto(user)));
			userDtos.setUsers(listOfUserDto);
		}

		return userDtos;
	}


	public UserDto userToUserDto(User user) {
		if (null == user) {
			return null;
		}
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setLanguage(user.getLanguage());
		userDto.setMobile(user.getMobile());
		userDto.setIsEnabled(user.getIsEnabled());
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		RoleDtos roleDtos = roleMapper.rolesToRoleDtos(user.getRoles());
		userDto.setRoles(roleDtos == null ? null : roleDtos.getRoles());
		return userDto;
	}

	public List<WhereClause> userSearchRequestToWhereClauses(UserSearchRequest userSearchRequest) {
		return getWhereClauses(userSearchRequest.getUserId(), userSearchRequest.getName(),
				userSearchRequest.getUsername(), userSearchRequest.getEmail(), userSearchRequest.getMobile());
	}

	private List<WhereClause> getWhereClauses(Long userId, String name, String username, String email, String mobile) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (userId != null) {
			WhereClause whereClause = new WhereClause("id", String.valueOf(userId), OperationType.EQUALS);
			whereClauses.add(whereClause);
		} else if (!StringUtils.isEmpty(username)) {
			WhereClause whereClause = new WhereClause("username", username, OperationType.EQUALS);
			whereClauses.add(whereClause);
		} else {
			if (!StringUtils.isEmpty(name)) {
				WhereClause whereClause = new WhereClause("firstName", name, OperationType.LIKE);
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(email)) {
				WhereClause whereClause = new WhereClause("email", email, OperationType.LIKE);
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(mobile)) {
				WhereClause whereClause = new WhereClause("mobile", mobile, OperationType.LIKE);
				whereClauses.add(whereClause);
			}
		}
		return whereClauses;
	}
}
