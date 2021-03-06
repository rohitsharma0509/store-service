package com.app.ecom.store.service.impl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import com.app.ecom.store.client.UserServiceClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.userservice.AddressDtos;
import com.app.ecom.store.dto.userservice.AddressSearchRequest;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.dto.userservice.UserDtos;
import com.app.ecom.store.dto.userservice.UserSearchRequest;
import com.app.ecom.store.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private LocaleResolver localeResolver;
    
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
    		if(loggedInUser != null && !StringUtils.isEmpty(userDto.getLastModifiedBy())) {
	    		userDto.setLastModifiedBy(loggedInUser.getUsername());
    		}
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
    	UserDto userDto = userServiceClient.getUser(id);
    	setAddressInUserDto(userDto);
    	return userDto;
	}
    
    @Override
    public UserDto findUserByUsername(String username) {
    	UserSearchRequest userSearchRequest = new UserSearchRequest();
    	userSearchRequest.setUsername(username);
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
    	UserDto userDto = userDtos == null || CollectionUtils.isEmpty(userDtos.getUsers()) ? null : userDtos.getUsers().get(0);
    	setAddressInUserDto(userDto);
        return userDto;
    }
    
    @Override
    public UserDto findUserByEmail(String email) {
    	UserSearchRequest userSearchRequest = new UserSearchRequest();
    	userSearchRequest.setEmail(email);
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
    	UserDto userDto = userDtos == null || CollectionUtils.isEmpty(userDtos.getUsers()) ? null : userDtos.getUsers().get(0);
    	setAddressInUserDto(userDto);
        return userDto;
    }
    
	@Override
	public List<UserDto> getUserByMobileOrName(String mobileOrName) {
		UserSearchRequest userSearchRequest = new UserSearchRequest();
    	userSearchRequest.setMobile(mobileOrName);
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
        return userDtos == null ? null : userDtos.getUsers();
	}
	
	@Override
	public List<UserDto> getAllUsers() {
		UserSearchRequest userSearchRequest = new UserSearchRequest();
    	UserDtos userDtos = userServiceClient.getUsers(userSearchRequest);
        return userDtos == null ? null : userDtos.getUsers();
	}
    
    @Override
	public CustomPage<UserDto> getUsers(Pageable pageable, Map<String, String> params) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		UserSearchRequest userSearchRequest = new UserSearchRequest();
		userSearchRequest.setName(params.get(FieldNames.NAME));
		userSearchRequest.setMobile(params.get(FieldNames.MOBILE));
    	userSearchRequest.setEmail(params.get(FieldNames.EMAIL));
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
	public boolean changePassword(Long userId, String pswrd) {
		return userServiceClient.changePassword(userId, bCryptPasswordEncoder.encode(pswrd));
	}
	
	private void setAddressInUserDto(UserDto userDto) {
		if(userDto != null) {
			AddressSearchRequest addressSearchRequest = new AddressSearchRequest();
	    	addressSearchRequest.setUserId(userDto.getId());
	    	AddressDtos addressDtos = userServiceClient.getAddresses(addressSearchRequest);
	    	userDto.setAddressDtos(addressDtos == null ? null : addressDtos.getAddresses());
		}
	}

	@Override
	public boolean modifyUserRoles(Long id, IdsDto idsDto) {
		return userServiceClient.modifyUserRoles(id, idsDto);
	}
}
