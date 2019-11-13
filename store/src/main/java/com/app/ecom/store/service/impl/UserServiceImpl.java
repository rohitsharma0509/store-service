package com.app.ecom.store.service.impl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.app.ecom.store.client.UserServiceClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.dto.userservice.UserDtos;
import com.app.ecom.store.dto.userservice.UserSearchRequest;
import com.app.ecom.store.events.ChangePasswordEvent;
import com.app.ecom.store.events.RegistrationCompleteEvent;
import com.app.ecom.store.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
    @Autowired
    private LocaleResolver localeResolver;
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UserServiceClient userServiceClient;
    
    @Autowired
    private HttpSession httpSession;
    
    @Override
    public UserDto createUser(UserDto userDto) {
    	if(!StringUtils.isEmpty(userDto.getPassword())) {
    		userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    	}
    	if(userDto.getId() == null) {    		
    		userDto.setCreatedBy(userDto.getUsername());
    		userDto.setCreatedTs(ZonedDateTime.now());
    	} else {
    		UserDto loggedInUser = (UserDto) httpSession.getAttribute(FieldNames.USER);
    		userDto.setLastModifiedBy(loggedInUser.getUsername());
    		userDto.setLastModifiedTs(ZonedDateTime.now());
    	}
    	return userServiceClient.createUpdateUser(userDto);
    }
    
    @Override
    public void updateUser(UserDto userDto) {
    	createUser(userDto);
    }
    
    @Override
	public UserDto findUserById(Long id) {
    	UserSearchRequest userSearchRequest = new UserSearchRequest();
    	userSearchRequest.setUserId(id);
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
        return userDtos == null || CollectionUtils.isEmpty(userDtos.getUsers()) ? null : userDtos.getUsers().get(0);
	}
    
    @Override
    public UserDto findUserByUsername(String username) {
    	UserSearchRequest userSearchRequest = new UserSearchRequest();
    	userSearchRequest.setUsername(username);
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
        return userDtos == null || CollectionUtils.isEmpty(userDtos.getUsers()) ? null : userDtos.getUsers().get(0);
    }
    
    @Override
    public UserDto findUserByEmail(String email) {
    	UserSearchRequest userSearchRequest = new UserSearchRequest();
    	userSearchRequest.setEmail(email);
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
        return userDtos == null || CollectionUtils.isEmpty(userDtos.getUsers()) ? null : userDtos.getUsers().get(0);
    }
    
	@Override
	public List<UserDto> getUserByMobileOrName(String mobileOrName) {
		UserSearchRequest userSearchRequest = new UserSearchRequest();
    	userSearchRequest.setMobile(mobileOrName);
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
        return userDtos == null ? null : userDtos.getUsers();
	}
    
    @Override
	public CustomPage<UserDto> getUsers(Pageable pageable, Map<String, String> params) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		UserSearchRequest userSearchRequest = new UserSearchRequest();
		userSearchRequest.setName(params.get("name"));
		userSearchRequest.setMobile(params.get("mobile"));
    	userSearchRequest.setEmail(params.get("email"));
    	userSearchRequest.setOffset(offset);
    	userSearchRequest.setLimit(limit);
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
    	Long totalRecords = userServiceClient.countUsers(userSearchRequest);
		
    	CustomPage<UserDto> page = new CustomPage<>();
		page.setContent(userDtos == null ? null : userDtos.getUsers());
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}
    
    @Override
    public void updateLocale(HttpServletRequest request, HttpServletResponse response, String language){
    	localeResolver.setLocale(request, response, Locale.forLanguageTag(language));
    }

    @Override
    @Transactional
    public void sendVerificationLink(UserDto userDto, HttpServletRequest request) {
    	StringBuilder url = new StringBuilder(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());
    	url.append(request.getContextPath());
    	logger.info("Registration complete Event url: "+url);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(url.toString(), request.getLocale(), userDto));
    }
	
	@Override
	@Transactional
    public void sendChangePasswordLink(UserDto userDto, HttpServletRequest request) {
    	StringBuilder url = new StringBuilder(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());
    	url.append(request.getContextPath());
    	logger.info("Change password Event url: "+url);
        applicationEventPublisher.publishEvent(new ChangePasswordEvent(url.toString(), request.getLocale(), userDto));
    }
}
