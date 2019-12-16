package com.app.ecom.store.validator;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.Email;
import com.app.ecom.store.enums.ErrorCode;
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.TO, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.SUBJECT, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.BODY, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));

		String[] to = commonUtil.convertStringToArray(email.getTo(), Constants.COMMA);
		if (!commonUtil.isValidEmails(to)) {
			errors.rejectValue(FieldNames.TO, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
		}
		String[] cc = commonUtil.convertStringToArray(email.getCc(), Constants.COMMA);
		if (!commonUtil.isValidEmails(cc)) {
			errors.rejectValue(FieldNames.CC, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
		}
		String[] bcc = commonUtil.convertStringToArray(email.getBcc(), Constants.COMMA);
		if (!commonUtil.isValidEmails(bcc)) {
			errors.rejectValue(FieldNames.BCC, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
		}
	}

}
