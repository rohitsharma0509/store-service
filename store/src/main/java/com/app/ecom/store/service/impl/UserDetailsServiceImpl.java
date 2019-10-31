package com.app.ecom.store.service.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.findUserByUsername(username);

        if(null != userDto){
        	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        	
        	userDto.getRoles().stream().filter(Objects::nonNull).forEach(role -> {
        		if(!CollectionUtils.isEmpty(role.getPrivilegeDtos())) {
        			role.getPrivilegeDtos().stream().filter(Objects::nonNull).forEach(privilege -> 
        				grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getName()))
        			);
        		}
        	});
        	
        	return new org.springframework.security.core.userdetails.User(userDto.getUsername(), userDto.getPassword(), userDto.isEnabled(), true, true, true, grantedAuthorities);
        }else {
        	throw new UsernameNotFoundException("Username not found");
        }
    }   
}