package com.app.ecom.store.mapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.app.ecom.store.dto.UserDto;
import com.app.ecom.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class UserMapper {
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private AddressMapper addressMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        if(!StringUtils.isEmpty(userDto.getUsername())) {
        	user.setUsername(userDto.getUsername());
        }
        if(!StringUtils.isEmpty(userDto.getPassword())) {
        	user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }
        boolean isEnabled = null != userDto.isEnabled() && userDto.isEnabled() ? true : false;
        user.setIsEnabled(isEnabled);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setMobile(userDto.getMobile());
        user.setLanguage(StringUtils.isEmpty(userDto.getLanguage()) ? "en" : userDto.getLanguage());
        user.setAddresses(addressMapper.addressDtosToAddress(userDto.getAddressDtos()));
        user.setRoles(roleMapper.roleDtosToRoles(userDto.getRoles()));
        return user;
    }
    
    public Set<UserDto> usersToUserDtos(Set<User> users) {
    	if(CollectionUtils.isEmpty(users)) {
    		return Collections.emptySet();
    	}
    	
    	Set<UserDto> userDtos = new HashSet<>();
    	users.stream().forEach(user -> userDtos.add(userToUserDto(user)));
    	return userDtos;
    }
    
    public UserDto userToUserDto(User user) {
    	if(null == user) {
    		return null;
    	}
    	
    	return userToUserDto(user, true);
    }
    public UserDto userToUserDto(User user, Boolean isAddressRequired) {
    	UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setLanguage(user.getLanguage());
        userDto.setMobile(user.getMobile());
        if(isAddressRequired) {
        	userDto.setAddressDtos(addressMapper.addressesToAddressDtos(user.getAddresses()));
        }
        return userDto;
    }
}
