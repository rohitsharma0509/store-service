package com.app.ecom.store.token.resource;

import com.app.ecom.store.token.constants.Endpoint;
import com.app.ecom.store.token.dto.UserTokenDto;
import com.app.ecom.store.token.service.UserTokenService;
import com.app.ecom.store.token.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTokenResource {
	
	private static final Logger logger = LogManager.getLogger(UserTokenResource.class);
	
	@Autowired
	private UserTokenService userTokenService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.TOKEN)
	public ResponseEntity<UserTokenDto> createUpdateToken(@RequestBody UserTokenDto userTokenDto) {
		try {
			UserTokenDto createdUserTokenDto = userTokenService.createUpdateToken(userTokenDto);
			return new ResponseEntity<>(createdUserTokenDto, userTokenDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while creating user token: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = Endpoint.TOKEN)
	public ResponseEntity<UserTokenDto> getToken(@RequestParam String token) {
		try {
			UserTokenDto userTokenDto = userTokenService.getToken(token);
			return new ResponseEntity<>(userTokenDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting user token: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
