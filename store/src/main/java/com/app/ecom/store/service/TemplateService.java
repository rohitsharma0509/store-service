package com.app.ecom.store.service;

import java.util.List;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.templateservice.TemplateDto;
import org.springframework.data.domain.Pageable;

public interface TemplateService {
	TemplateDto getTemplateById(Long id);

	TemplateDto addTemplate(TemplateDto templateDto);

	CustomPage<TemplateDto> getTemplates(Pageable pageable);

	void deleteTemplates(IdsDto idsDto);

	List<TemplateDto> getAllTemplates();
}
