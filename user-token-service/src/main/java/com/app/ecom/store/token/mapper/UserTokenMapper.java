package com.app.ecom.store.token.mapper;

import com.app.ecom.store.token.dto.UserTokenDto;
import com.app.ecom.store.token.model.UserToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserTokenMapper {
	
	private static final Logger logger = LogManager.getLogger(UserTokenMapper.class);

	public UserTokenDto userTokenToUserTokenDto(UserToken userToken) {
		if(userToken == null) {
			return null;
		}
		
		UserTokenDto userTokenDto = new UserTokenDto();
        userTokenDto.setCreatedBy(userToken.getCreatedBy());
        userTokenDto.setCreatedTs(userToken.getCreatedTs());
        userTokenDto.setLastModifiedBy(userToken.getLastModifiedBy());
        userTokenDto.setLastModifiedTs(userToken.getLastModifiedTs());
        userTokenDto.setToken(userToken.getToken());
        userTokenDto.setExpiryDate(userToken.getExpiryDate());
        userTokenDto.setUserId(userToken.getUserId());
		return userTokenDto;
	}
	
	public UserToken userTokenDtoToUserToken(UserTokenDto userTokenDto) {
		if(userTokenDto == null) {
			return null;
		}
		
		UserToken userToken = new UserToken();
        userToken.setCreatedBy(userTokenDto.getCreatedBy());
        userToken.setCreatedTs(userTokenDto.getCreatedTs());
        userToken.setLastModifiedBy(userTokenDto.getLastModifiedBy());
        userToken.setLastModifiedTs(userTokenDto.getLastModifiedTs());
        userToken.setToken(userTokenDto.getToken());
        userToken.setExpiryDate(userTokenDto.getExpiryDate());
        userToken.setUserId(userTokenDto.getUserId());
		return userToken;
	}
}
