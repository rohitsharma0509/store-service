package com.app.ecom.store.mapper;

import com.app.ecom.store.dto.UserTokenDto;
import com.app.ecom.store.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTokenMapper {
	
	@Autowired
	private UserMapper userMapper;

	public UserTokenDto userTokenToUserTokenDto(UserToken userToken) {
		if(userToken == null) {
			return null;
		}
		
		UserTokenDto userTokenDto = new UserTokenDto();
		userTokenDto.setId(userToken.getId());
		userTokenDto.setToken(userToken.getToken());
		userTokenDto.setExpiryDate(userToken.getExpiryDate());
		userTokenDto.setUserDto(userMapper.userToUserDto(userToken.getUser()));
		return userTokenDto;
	}
	
	public UserToken userTokenDtoToUserToken(UserTokenDto userTokenDto) {
		if(userTokenDto == null) {
			return null;
		}
		
		UserToken userToken = new UserToken();
		userToken.setId(userTokenDto.getId());
		userToken.setToken(userTokenDto.getToken());
		userToken.setExpiryDate(userTokenDto.getExpiryDate());
		userToken.setUser(userMapper.userDtoToUser(userTokenDto.getUserDto()));
		return userToken;
	}
}
