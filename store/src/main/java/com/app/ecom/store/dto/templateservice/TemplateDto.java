package com.app.ecom.store.dto.templateservice;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class TemplateDto {

	@JsonProperty("id")
    private Long id;

	@JsonProperty("type")
    private Long type;

	@JsonProperty("subject")
    private String subject;
	
	@JsonProperty("oldSubject")
	private String oldSubject;

	@JsonProperty("body")
    private String body;
	
	@JsonProperty("isEditable")
    private boolean isEditable;
    
	@JsonProperty("isDeletable")
    private boolean isDeletable;
	
	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("createdTs")
	private ZonedDateTime createdTs;

	@JsonProperty("lastModifiedBy")
	private String lastModifiedBy;

	@JsonProperty("lastModifiedTs")
	private ZonedDateTime lastModifiedTs;

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
	
	public boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public boolean getIsDeletable() {
		return isDeletable;
	}

	public void setIsDeletable(boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public ZonedDateTime getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(ZonedDateTime createdTs) {
		this.createdTs = createdTs;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public ZonedDateTime getLastModifiedTs() {
		return lastModifiedTs;
	}

	public void setLastModifiedTs(ZonedDateTime lastModifiedTs) {
		this.lastModifiedTs = lastModifiedTs;
	}
}
