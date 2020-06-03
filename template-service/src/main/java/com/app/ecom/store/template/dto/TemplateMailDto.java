package com.app.ecom.store.template.dto;

import java.io.Serializable;
import java.util.Map;

import com.app.ecom.store.template.enums.MailTemplateName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class TemplateMailDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("from")
    private String from;
	
	@JsonProperty("to")
    private String[] to;
	
	@JsonProperty("templateName")
    private MailTemplateName templateName;
	
	@JsonProperty("trackingId")
	private String trackingId;
	
	private Map<String, Object> variables;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

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

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

}
