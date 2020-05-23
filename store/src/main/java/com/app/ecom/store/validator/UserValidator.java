package com.app.ecom.store.validator;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.UserService;
import com.app.ecom.store.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;
    
    @Autowired
    private CommonUtil commonUtil;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.FIRST_NAME, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.LAST_NAME, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.USERNAME, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.MOBILE, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        if (userDto.getUsername().length() < 5 || userDto.getUsername().length() > 32) {
        	errors.rejectValue(FieldNames.USERNAME, commonUtil.getMessage(ErrorCode.ERR000004.getCode()));
        }
        if (userService.findUserByUsername(userDto.getUsername()) != null) {
        	errors.rejectValue(FieldNames.USERNAME, commonUtil.getMessage(ErrorCode.ERR000005.getCode()));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.PASSWORD, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        if (userDto.getPassword().length() < 8 || userDto.getPassword().length() > 32) {
        	errors.rejectValue(FieldNames.PASSWORD, commonUtil.getMessage(ErrorCode.ERR000006.getCode()));
        }
        if (!userDto.getPasswordConfirm().equals(userDto.getPassword())) {
        	errors.rejectValue(FieldNames.PASSWORD_CONFIRM, commonUtil.getMessage(ErrorCode.ERR000007.getCode()));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.EMAIL, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        if (!StringUtils.isEmpty(userDto.getEmail()) && !commonUtil.isValidEmail(userDto.getEmail())) {
        	errors.rejectValue(FieldNames.EMAIL, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
        }
        if (userDto.getMobile().length() < 10) {
        	errors.rejectValue(FieldNames.MOBILE, commonUtil.getMessage(ErrorCode.ERR000014.getCode()));
        }
    }

	public Response validatePassword(String pswrd, String confirmPswrd) {
		if(StringUtils.isEmpty(pswrd) || StringUtils.isEmpty(confirmPswrd)) {
			return commonUtil.getResponse(true, ErrorCode.ERR000003.getCode());
		}
		
		boolean isPswrdMatch = pswrd.equals(confirmPswrd);
		return commonUtil.getResponse(!isPswrdMatch, ErrorCode.ERR000007.getCode());
	}

	public Response validateRoleIds(IdsDto idsDto) {
		if(idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			return commonUtil.getResponse(true, ErrorCode.ERR000003.getCode());
		} else {
			return commonUtil.getResponse(false, null);
		}
	}
}
