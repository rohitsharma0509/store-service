package com.app.ecom.store.template.service;

import com.app.ecom.store.template.dto.IdsDto;
import com.app.ecom.store.template.dto.TemplateDto;
import com.app.ecom.store.template.dto.TemplateDtos;
import com.app.ecom.store.template.dto.TemplateSearchRequest;

public interface TemplateService {

	TemplateDto createUpdateTemplate(TemplateDto templateDto);

	TemplateDto getTemplateById(Long id);
	
	TemplateDtos getTemplates(TemplateSearchRequest templateSearchRequest);

	Long countTemplates(TemplateSearchRequest templateSearchRequest);

	void deleteTemplates(IdsDto idsDto);
	
}
