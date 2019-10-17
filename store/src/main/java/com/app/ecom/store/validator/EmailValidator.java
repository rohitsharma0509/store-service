package com.app.ecom.store.validator;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.Email;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator {

	@Autowired
	private CommonUtil commonUtil;

	@Override
	public boolean supports(Class<?> aClass) {
		return Email.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Email email = (Email) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "to", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "NotEmpty");

		String[] to = commonUtil.convertStringToArray(email.getTo(), Constants.COMMA);
		if(null != to && to.length>0){
			for (String emailTo : to) {
				if (!emailTo.matches("[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
					errors.rejectValue("to", "Email.Address.Not.Valid");
				}
			}
		}
	}

}
