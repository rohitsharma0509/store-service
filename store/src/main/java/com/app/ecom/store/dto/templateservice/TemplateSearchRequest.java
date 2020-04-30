package com.app.ecom.store.dto.templateservice;

import com.app.ecom.store.dto.DefaultSearchRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class TemplateSearchRequest extends DefaultSearchRequest {
	@JsonProperty("templateId")
	private Long templateId;
	
	@JsonProperty("templateType")
	private Long templateType;

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Long templateType) {
		this.templateType = templateType;
	}
}
