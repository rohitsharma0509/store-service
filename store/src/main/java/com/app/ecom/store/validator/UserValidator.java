package com.app.ecom.store.validator;

import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.UserService;
import com.app.ecom.store.util.CommonUtil;
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        if (userDto.getUsername().length() < 5 || userDto.getUsername().length() > 32) {
        	errors.rejectValue("username", commonUtil.getMessage(ErrorCode.ERR000004.getCode()));
        }
        if (userService.findUserByUsername(userDto.getUsername()) != null) {
        	errors.rejectValue("username", commonUtil.getMessage(ErrorCode.ERR000005.getCode()));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (userDto.getPassword().length() < 8 || userDto.getPassword().length() > 32) {
        	errors.rejectValue("password", commonUtil.getMessage(ErrorCode.ERR000006.getCode()));
        }
        if (!userDto.getPasswordConfirm().equals(userDto.getPassword())) {
        	errors.rejectValue("passwordConfirm", commonUtil.getMessage(ErrorCode.ERR000007.getCode()));
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!userDto.getEmail().matches("[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
        	errors.rejectValue("email", commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
        }
        if (userDto.getMobile().length() < 10) {
        	errors.rejectValue("mobile", commonUtil.getMessage(ErrorCode.ERR000014.getCode()));
        }
    }
}
