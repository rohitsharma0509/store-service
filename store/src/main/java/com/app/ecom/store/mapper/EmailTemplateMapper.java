package com.app.ecom.store.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.app.ecom.store.dto.EmailTemplateDto;
import com.app.ecom.store.model.EmailTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class EmailTemplateMapper {
    
    public List<EmailTemplateDto> emailTemplatesToEmailTemplateDtos(List<EmailTemplate> emailTemplates) {
        if(CollectionUtils.isEmpty(emailTemplates)) {
            return Collections.emptyList();
        }
        
        List<EmailTemplateDto> emailTemplateDtos = new ArrayList<>();
        emailTemplates.stream().forEach(emailTemplate -> emailTemplateDtos.add(emailTemplateToEmailTemplateDto(emailTemplate)));
        return emailTemplateDtos;
    }
    
    public EmailTemplateDto emailTemplateToEmailTemplateDto(EmailTemplate emailTemplate) {
    	if(emailTemplate == null) {
    		return null;
    	}
    	
        EmailTemplateDto emailTemplateDto = new EmailTemplateDto();
        emailTemplateDto.setId(emailTemplate.getId());
        emailTemplateDto.setType(emailTemplate.getType());
        emailTemplateDto.setSubject(emailTemplate.getSubject());
        emailTemplateDto.setFrom(emailTemplate.getFrom());
        emailTemplateDto.setTo(emailTemplate.getTo());
        emailTemplateDto.setCc(emailTemplate.getCc());
        emailTemplateDto.setBcc(emailTemplate.getBcc());
        emailTemplateDto.setBody(emailTemplate.getBody());
        return emailTemplateDto;
    }
    
    public List<EmailTemplate> emailTemplateDtosToEmailTemplates(List<EmailTemplateDto> emailTemplateDtos) {
        if(CollectionUtils.isEmpty(emailTemplateDtos)) {
            return Collections.emptyList();
        }
        
        List<EmailTemplate> emailTemplates = new ArrayList<>();
        emailTemplateDtos.stream().forEach(emailTemplateDto -> emailTemplates.add(emailTemplateDtoToEmailTemplate(emailTemplateDto)));
        return emailTemplates;
    }
    
    public EmailTemplate emailTemplateDtoToEmailTemplate(EmailTemplateDto emailTemplateDto) {
    	if(emailTemplateDto == null) {
    		return null;
    	}
    	
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setId(emailTemplateDto.getId());
        emailTemplate.setType(emailTemplateDto.getType());
        emailTemplate.setSubject(emailTemplateDto.getSubject());
        emailTemplate.setFrom(emailTemplateDto.getFrom());
        emailTemplate.setTo(emailTemplateDto.getTo());
        emailTemplate.setCc(emailTemplateDto.getCc());
        emailTemplate.setBcc(emailTemplateDto.getBcc());
        emailTemplate.setBody(emailTemplateDto.getBody());
        return emailTemplate;
    }
}
