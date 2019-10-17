package com.app.ecom.store.service;

import java.util.List;

import com.app.ecom.store.dto.EmailTemplateDto;
import com.app.ecom.store.model.EmailTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmailTemplateService {

    EmailTemplateDto getEmailTemplateById(Long id);
    
    EmailTemplateDto getEmailTemplateBySubject(String name);

    void addEmailTemplate(EmailTemplateDto emailTemplateDto);

    Page<EmailTemplate> getEmailTemplates(Pageable pageable);

	void deleteEmailTemplateById(Long id);

	boolean deleteEmailTemplates(List<Long> ids);

	boolean deleteAllEmailTemplates();

	List<EmailTemplateDto> getAllEmailTemplates();
}
