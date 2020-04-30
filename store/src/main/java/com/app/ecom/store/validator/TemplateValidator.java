package com.app.ecom.store.validator;

import java.util.List;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.templateservice.TemplateDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.TemplateService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TemplateValidator implements Validator {
    
	@Autowired
	private TemplateService templateService;
	
    @Autowired
    private CommonUtil commonUtil;

    @Override
    public boolean supports(Class<?> aClass) {
        return TemplateDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TemplateDto templateDto = (TemplateDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.TO, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.SUBJECT, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.BODY, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));

        String[] to = commonUtil.convertStringToArray(templateDto.getTo(), Constants.COMMA);
        if(!commonUtil.isValidEmails(to)){
        	errors.rejectValue(FieldNames.TO, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
        }
        
        String[] cc = commonUtil.convertStringToArray(templateDto.getCc(), Constants.COMMA);
        if(!commonUtil.isValidEmails(cc)){
        	errors.rejectValue(FieldNames.CC, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
        }
        
        String[] bcc = commonUtil.convertStringToArray(templateDto.getBcc(), Constants.COMMA);
        if(!commonUtil.isValidEmails(bcc)){
        	errors.rejectValue(FieldNames.BCC, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
        }
        
        List<TemplateDto> templateDtos = templateService.getAllTemplates();
		
		for(TemplateDto template : templateDtos) {
			if(!template.getSubject().equalsIgnoreCase(templateDto.getOldSubject()) && template.getSubject().equalsIgnoreCase(templateDto.getSubject())) {
				errors.rejectValue(FieldNames.NAME, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
			}
		}
    }

	public Response validateTemplateAssociation(List<Long> templateIds) {
		return commonUtil.getResponse(false, null);
	}
}
