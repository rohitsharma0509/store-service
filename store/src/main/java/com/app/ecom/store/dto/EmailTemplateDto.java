package com.app.ecom.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class EmailTemplateDto {

	@JsonProperty("id")
    private Long id;

	@JsonProperty("type")
    private Long type;

	@JsonProperty("from")
    private String from;

	@JsonProperty("to")
    private String to;

	@JsonProperty("cc")
    private String cc;

	@JsonProperty("bcc")
    private String bcc;

	@JsonProperty("subject")
    private String subject;
	
	@JsonProperty("oldSubject")
	private String oldSubject;

	@JsonProperty("body")
    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOldSubject() {
		return oldSubject;
	}

	public void setOldSubject(String oldSubject) {
		this.oldSubject = oldSubject;
	}

	public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
