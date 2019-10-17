package com.app.ecom.store.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.app.ecom.store.dto.UserDto;
import com.app.ecom.store.dto.UserTokenDto;
import com.app.ecom.store.mapper.UserMapper;
import com.app.ecom.store.mapper.UserTokenMapper;
import com.app.ecom.store.model.UserToken;
import com.app.ecom.store.repository.UserTokenRepository;
import com.app.ecom.store.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;
    
    @Autowired
    private UserTokenMapper userTokenMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    private static final int TOKEN_EXPIRY_TIME = 24 * 60;

    @Override
    public String createUserToken(UserDto userDto) {
        String token = UUID.randomUUID().toString();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, TOKEN_EXPIRY_TIME);
        Date expiryDate = new Date(cal.getTime().getTime());
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setExpiryDate(expiryDate);
        userToken.setUser(userMapper.userDtoToUser(userDto));
        userTokenRepository.save(userToken);
        return token;
    }

    @Override
    public UserTokenDto getUserToken(String token) {
        return userTokenMapper.userTokenToUserTokenDto(userTokenRepository.findByToken(token));
    }
}
