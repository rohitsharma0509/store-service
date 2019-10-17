package com.app.ecom.store.service.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.app.ecom.store.model.User;
import com.app.ecom.store.repository.UserRepository;
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
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if(null != user){
        	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        	
        	user.getRoles().stream().filter(Objects::nonNull).forEach(role -> {
        		if(!CollectionUtils.isEmpty(role.getPrivileges())) {
        			role.getPrivileges().stream().filter(Objects::nonNull).forEach(privilege -> 
        				grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getName()))
        			);
        		}
        	});
        	
        	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getIsEnabled(), true, true, true, grantedAuthorities);
        }else {
        	throw new UsernameNotFoundException("Username not found");
        }
    }   
}