package com.app.ecom.store.validator;

import java.util.List;

import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.masterdata.SettingDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.SettingService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SettingValidator implements Validator {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private SettingService settingService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return SettingDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		SettingDto settingDto = (SettingDto) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		
		List<SettingDto> settingDtos = settingService.getAllSettings();
		
		for(SettingDto setting : settingDtos) {
			if(!setting.getName().equalsIgnoreCase(settingDto.getOldName()) && setting.getName().equalsIgnoreCase(settingDto.getName())) {
				errors.rejectValue("name", commonUtil.getMessage(ErrorCode.ERR000027.getCode()));
			}
		}
	}

	public Response validateSettingAssociation(List<Long> ids) {
		return commonUtil.getResponse(ids == null, "");
	}
}
