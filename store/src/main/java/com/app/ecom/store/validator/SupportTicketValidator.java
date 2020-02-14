package com.app.ecom.store.validator;

import java.util.List;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.productservice.ProductDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SupportTicketValidator implements Validator {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return ProductDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.ORDER_NUMBER, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.DESCRIPTION, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
	}

	public Response validateSupportTicketAssociation(List<Long> supportTicketIds) {
		return commonUtil.getResponse(false, null);
	}
}
