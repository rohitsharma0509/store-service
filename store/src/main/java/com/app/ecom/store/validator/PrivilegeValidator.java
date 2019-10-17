package com.app.ecom.store.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.app.ecom.store.dto.PrivilegeDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.PrivilegeService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PrivilegeValidator implements Validator {
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return PrivilegeDto.class.equals(aClass);
	}
	
	@Override
	public void validate(Object o, Errors errors) {
		PrivilegeDto privilegeDto = (PrivilegeDto) o;
		
		List<PrivilegeDto> privilegeDtos = privilegeService.getAllPrivileges();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		
		for(PrivilegeDto privilege : privilegeDtos) {
			if(!privilege.getName().equalsIgnoreCase(privilegeDto.getOldName()) && privilege.getName().equalsIgnoreCase(privilegeDto.getName())) {
				errors.rejectValue("name", commonUtil.getMessage(ErrorCode.ERR000022.getCode()));
			}
		}
	}
	
	public Response validatePrivilegeAssociation(List<Long> privilegeIds) {
		List<PrivilegeDto> privilegeDtos = new ArrayList<>();
		if(CollectionUtils.isEmpty(privilegeIds)) {
			privilegeDtos = privilegeService.getAllPrivileges();
			privilegeIds = privilegeDtos.stream().map(PrivilegeDto::getId).collect(Collectors.toList());
		}
		
		String errorCode = privilegeDtos.size() <= 1 ? ErrorCode.ERR000023.getCode() : ErrorCode.ERR000024.getCode();
		return commonUtil.getResponse(privilegeService.isRoleAssociatedWithPrivileges(privilegeIds), errorCode);
	}	
}
