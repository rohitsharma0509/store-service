package com.app.ecom.store.service;

import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.dto.userservice.UserTokenDto;

public interface UserTokenService {

    String createUserToken(UserDto userDto);
    
    UserTokenDto getUserToken(String token);

}
