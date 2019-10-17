package com.app.ecom.store.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.app.ecom.store.dto.EmailTemplateDto;
import com.app.ecom.store.mapper.EmailTemplateMapper;
import com.app.ecom.store.model.EmailTemplate;
import com.app.ecom.store.repository.EmailTemplateRepository;
import com.app.ecom.store.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private EmailTemplateMapper emailTemplateMapper;

    @Override
    public EmailTemplateDto getEmailTemplateById(Long id) {
        Optional<EmailTemplate> optional = emailTemplateRepository.findById(id);
        if (optional.isPresent()) {
            return emailTemplateMapper.emailTemplateToEmailTemplateDto(optional.get());
        } else {
            return null;
        }
    }
    
    @Override
    public EmailTemplateDto getEmailTemplateBySubject(String subject) {
        return emailTemplateMapper.emailTemplateToEmailTemplateDto(emailTemplateRepository.findBySubject(subject));
    }

    @Override
    public void addEmailTemplate(EmailTemplateDto emailTemplateDto) {
        emailTemplateRepository.save(emailTemplateMapper.emailTemplateDtoToEmailTemplate(emailTemplateDto));
    }

    @Override
    public Page<EmailTemplate> getEmailTemplates(Pageable pageable) {
        PageRequest request = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        return emailTemplateRepository.findAll(request);
    }

	@Override
	@Transactional
	public void deleteEmailTemplateById(Long id) {
		emailTemplateRepository.deleteById(id);
	}

	@Override
	@Transactional
	public boolean deleteEmailTemplates(List<Long> ids) {
		boolean isDeleted = false;
		try {
			emailTemplateRepository.deleteByIdIn(ids);
			isDeleted = true;
		} catch(Exception e) {
			
		}
		return isDeleted;
	}

	@Override
	@Transactional
	public boolean deleteAllEmailTemplates() {
		boolean isDeleted = false;
		try {
			emailTemplateRepository.deleteAll();
			isDeleted = true;
		} catch(Exception e) {
			
		}
		return isDeleted;
	}

	@Override
	public List<EmailTemplateDto> getAllEmailTemplates() {
		return emailTemplateMapper.emailTemplatesToEmailTemplateDtos(emailTemplateRepository.findAll());
	}
}
