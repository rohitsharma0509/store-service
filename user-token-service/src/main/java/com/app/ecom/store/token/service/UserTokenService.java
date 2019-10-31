package com.app.ecom.store.token.service;

import com.app.ecom.store.token.dto.UserTokenDto;

public interface UserTokenService {

	UserTokenDto createUpdateToken(UserTokenDto userTokenDto);

	UserTokenDto getToken(String token);

}
