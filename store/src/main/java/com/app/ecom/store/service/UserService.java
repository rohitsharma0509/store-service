package com.app.ecom.store.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.userservice.UserDto;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto createUser(UserDto userDto);
    
    void updateUser(UserDto userDto);

    UserDto findUserByEmail(String email);
    
    UserDto findUserByUsername(String username);
    
    List<UserDto> getAllUsers();
    
    CustomPage<UserDto> getUsers(Pageable pageable, Map<String, String> params);
    
    List<UserDto> getUserByMobileOrName(String mobileOrName);

    void updateLocale(HttpServletRequest request, HttpServletResponse response, String language);

    UserDto findUserById(Long id);

	boolean changePassword(Long userId, String pswrd);

	boolean modifyUserRoles(Long id, IdsDto idsDto);
}
