package com.app.ecom.store.service;

import com.app.ecom.store.dto.UserDto;
import com.app.ecom.store.dto.UserTokenDto;

public interface UserTokenService {

    String createUserToken(UserDto userDto);
    
    UserTokenDto getUserToken(String token);

}
