package com.app.ecom.store.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.inventory.InventoryDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.util.CommonUtil;

@Component
public class InventoryValidator implements Validator {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return InventoryDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		//InventoryDto inventoryDto = (InventoryDto) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.AGENCY_NAME, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.PAYMENT_MODE, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.PAYMENT_STATUS, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
	}
}
