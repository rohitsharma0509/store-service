package com.app.ecom.store.service.impl;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.app.ecom.store.client.UserTokenServiceClient;
import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.dto.usertokenservice.UserTokenDto;
import com.app.ecom.store.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenServiceClient userTokenServiceClient;
    
    private static final int TOKEN_EXPIRY_TIME = 24 * 60;

    @Override
    public String createUserToken(UserDto userDto) {
        String token = UUID.randomUUID().toString();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, TOKEN_EXPIRY_TIME);
        Date expiryDate = new Date(cal.getTime().getTime());
        
        UserTokenDto userTokenDto = new UserTokenDto();
        userTokenDto.setCreatedBy(userDto.getUsername());
        userTokenDto.setCreatedTs(ZonedDateTime.now());
        userTokenDto.setLastModifiedBy(userDto.getUsername());
        userTokenDto.setLastModifiedTs(ZonedDateTime.now());
        userTokenDto.setToken(token);
        userTokenDto.setExpiryDate(expiryDate);
        userTokenDto.setUserId(userDto.getId());
        UserTokenDto tokenDto = userTokenServiceClient.createToken(userTokenDto);
        return tokenDto == null ? Constants.EMPTY_STRING : tokenDto.getToken();
    }

    @Override
    public UserTokenDto getUserToken(String token) {
        return userTokenServiceClient.getToken(token);
    }
}
