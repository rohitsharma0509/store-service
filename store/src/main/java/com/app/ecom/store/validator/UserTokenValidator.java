package com.app.ecom.store.validator;

import java.util.Calendar;

import com.app.ecom.store.dto.usertokenservice.UserTokenDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserTokenValidator implements Validator {
	
	private static final Logger logger = LogManager.getLogger(UserTokenValidator.class);
	
	@Autowired
	private CommonUtil commonUtil;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserTokenDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserTokenDto userTokenDto = (UserTokenDto) o;
		if (userTokenDto == null) {
        	logger.info("Invalid token");
        	errors.rejectValue("token", commonUtil.getMessage(ErrorCode.ERR000025.getCode()));
        } else {
	        Calendar cal = Calendar.getInstance();
	        if ((userTokenDto.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        	logger.info("Token has been expired");
	        	errors.rejectValue("token", commonUtil.getMessage(ErrorCode.ERR000026.getCode()));
	        }
        }
	}
}
