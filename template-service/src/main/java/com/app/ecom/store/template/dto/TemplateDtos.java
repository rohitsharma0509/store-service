package com.app.ecom.store.template.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class TemplateDtos {
	@JsonProperty("templateDtos")
	private List<TemplateDto> templates;

	public List<TemplateDto> getTemplates() {
		return templates;
	}

	public void setTemplates(List<TemplateDto> templates) {
		this.templates = templates;
	}
}
