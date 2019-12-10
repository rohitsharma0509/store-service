package com.app.ecom.store.validator;

import java.util.List;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.EmailTemplateDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.EmailTemplateService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmailTemplateValidator implements Validator {
    
	private static final String EMAIL_PATTERN = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}";

	@Autowired
	private EmailTemplateService emailTemplateService;
	
    @Autowired
    private CommonUtil commonUtil;

    @Override
    public boolean supports(Class<?> aClass) {
        return EmailTemplateDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmailTemplateDto email = (EmailTemplateDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.TO, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.SUBJECT, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.BODY, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));

        String[] to = commonUtil.convertStringToArray(email.getTo(), Constants.COMMA);
        if(null != to && to.length>0){
            for (String emailTo : to) {
                if (!emailTo.matches(EMAIL_PATTERN)) {
                    errors.rejectValue(FieldNames.TO, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
                }
            }
        }
        
        List<EmailTemplateDto> emailTemplateDtos = emailTemplateService.getAllEmailTemplates();
		
		for(EmailTemplateDto emailTemplate : emailTemplateDtos) {
			if(!emailTemplate.getSubject().equalsIgnoreCase(email.getOldSubject()) && emailTemplate.getSubject().equalsIgnoreCase(email.getSubject())) {
				errors.rejectValue(FieldNames.NAME, commonUtil.getMessage(ErrorCode.ERR000008.getCode()));
			}
		}
    }

	public Response validateTemplateAssociation(List<Long> templateIds) {
		return commonUtil.getResponse(false, null);
	}
}
