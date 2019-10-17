package com.app.ecom.store.service;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.UserDto;
import com.app.ecom.store.model.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto createUser(UserDto userDto);
    
    void updateUser(UserDto userDto);

    User findByUsername(String username);
    
    UserDto findUserByEmail(String email);
    
    UserDto findUserByUsername(String username);
    
    CustomPage<UserDto> getUsers(Pageable pageable, Map<String, String> params);
    
    Set<UserDto> getUserByMobileOrName(String mobileOrName);

    void updateLocale(HttpServletRequest request, HttpServletResponse response, String language);

    void sendVerificationLink(UserDto userDto, HttpServletRequest request);

	UserDto findUserById(Long id);
	
	void sendChangePasswordLink(UserDto userDto, HttpServletRequest request);
}
