package com.app.ecom.store.validator;

import java.util.List;
import java.util.stream.Collectors;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.userservice.RoleDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.RoleService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return RoleDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		RoleDto roleDto = (RoleDto) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.NAME, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		
		List<RoleDto> roleDtos = roleService.getAllRoles();
		
		for(RoleDto role : roleDtos) {
			if(!role.getName().equalsIgnoreCase(roleDto.getOldName()) && role.getName().equalsIgnoreCase(roleDto.getName())) {
				errors.rejectValue(FieldNames.NAME, commonUtil.getMessage(ErrorCode.ERR000015.getCode()));
			}
		}
	}
	
	
	public Response validateRoleAssociation(List<Long> roleIds) {
		List<RoleDto> roleDtos;
		if(CollectionUtils.isEmpty(roleIds)) {
			roleDtos = roleService.getAllRoles();
		} else {
			roleDtos = roleService.getRolesByIdIn(roleIds);
		}
		String errorCode = roleDtos.size() > 1 ? ErrorCode.ERR000002.getCode() : ErrorCode.ERR000001.getCode();
		return checkIfAnyRoleAssociatedWithUser(roleDtos, errorCode);
	}
	
	public Response checkIfAnyRoleAssociatedWithUser(List<RoleDto> roleDtos, String errorCode) {
		List<RoleDto> roles = roleDtos.stream().filter(roleDto -> {
			boolean flag = false;
			if (null != roleDto && !CollectionUtils.isEmpty(roleDto.getUserDtos()) && roleDto.getUserDtos().size() > 0) {
				flag = true;
			}
			return flag;
		}).collect(Collectors.toList());
		
		boolean flag = false;
		if(!CollectionUtils.isEmpty(roles) && roles.size() > 0) {
			flag = true;
		}
		
		return commonUtil.getResponse(flag, errorCode);
	}
}
