package com.app.ecom.store.template.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.app.ecom.store.template.dto.TemplateDto;
import com.app.ecom.store.template.dto.TemplateDtos;
import com.app.ecom.store.template.dto.TemplateSearchRequest;
import com.app.ecom.store.template.dto.WhereClause;
import com.app.ecom.store.template.enums.OperationType;
import com.app.ecom.store.template.model.Template;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class TemplateMapper {
	public TemplateDtos templatesToTemplateDtos(List<Template> templates) {
		TemplateDtos templateDtos = new TemplateDtos();
		if(CollectionUtils.isEmpty(templates)) {
			templateDtos.setTemplates(Collections.emptyList());
		} else {			
			List<TemplateDto> listOfTemplateDto = new ArrayList<>();
			templates.stream().forEach(template -> listOfTemplateDto.add(templateToTemplateDto(template)));
			templateDtos.setTemplates(listOfTemplateDto);
		}
		return templateDtos;
	}
	
	public TemplateDto templateToTemplateDto(Template template) {
		if(null == template) {
			return null;
		}
		
		TemplateDto templateDto = new TemplateDto();
		templateDto.setId(template.getId());
        templateDto.setType(template.getType());
        templateDto.setSubject(template.getSubject());
        templateDto.setFrom(template.getFrom());
        templateDto.setTo(template.getTo());
        templateDto.setCc(template.getCc());
        templateDto.setBcc(template.getBcc());
        templateDto.setBody(template.getBody());		
		templateDto.setCreatedBy(template.getCreatedBy());
		templateDto.setCreatedTs(template.getCreatedTs());
		templateDto.setLastModifiedBy(template.getLastModifiedBy());
		templateDto.setLastModifiedTs(template.getLastModifiedTs());
		return templateDto;
	}
	
	public List<Template> templateDtosToTemplate(Set<TemplateDto> templateDtos) {
		if(CollectionUtils.isEmpty(templateDtos)) {
			return Collections.emptyList();
		}
		
		List<Template> templates = new ArrayList<>();
		templateDtos.stream().forEach(templateDto -> templates.add(templateDtoToTemplate(templateDto)));
		return templates;
	}
	
	public Template templateDtoToTemplate(TemplateDto templateDto) {
		if(null == templateDto) {
			return null;
		}
		
		Template template = new Template();
		template.setId(templateDto.getId());
        template.setType(templateDto.getType());
        template.setSubject(templateDto.getSubject());
        template.setFrom(templateDto.getFrom());
        template.setTo(templateDto.getTo());
        template.setCc(templateDto.getCc());
        template.setBcc(templateDto.getBcc());
        template.setBody(templateDto.getBody());
		template.setCreatedBy(templateDto.getCreatedBy());
		template.setCreatedTs(templateDto.getCreatedTs());
		template.setLastModifiedBy(templateDto.getLastModifiedBy());
		template.setLastModifiedTs(templateDto.getLastModifiedTs());
		return template;
	}

	public List<WhereClause> templateSearchRequestToWhereClauses(TemplateSearchRequest templateSearchRequest) {
		return getWhereClauses(templateSearchRequest.getTemplateId(), templateSearchRequest.getTemplateType());
	}

	private List<WhereClause> getWhereClauses(Long templateId, Long templateType) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (templateId != null) {
			WhereClause whereClause = new WhereClause("id", String.valueOf(templateId), OperationType.EQUALS);
			whereClauses.add(whereClause);
		} else {
			if (templateType != null) {
				WhereClause whereClause = new WhereClause("type", String.valueOf(templateType), OperationType.EQUALS);
				whereClauses.add(whereClause);
			}
		}
		return whereClauses;
	}
}
