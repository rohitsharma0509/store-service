package com.app.ecom.store.userservice.service;

import com.app.ecom.store.userservice.dto.UserTokenDto;

public interface UserTokenService {

	UserTokenDto createUpdateToken(UserTokenDto userTokenDto);

	UserTokenDto getToken(String token);

}
