package com.app.ecom.store.dto.templateservice;

import java.io.Serializable;
import java.util.Map;

import com.app.ecom.store.enums.MailTemplateName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class TemplateMailDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("to")
    private String[] to;
	
	@JsonProperty("templateName")
    private MailTemplateName templateName;
	
	@JsonProperty("trackingId")
	private String trackingId;
	
	private Map<String, String> variables;

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public MailTemplateName getTemplateName() {
		return templateName;
	}

	public void setTemplateName(MailTemplateName templateName) {
		this.templateName = templateName;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public Map<String, String> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

}
