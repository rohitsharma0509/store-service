package com.app.ecom.store.support.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "support_ticket_activity_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SupportTicketActivityHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_history_id")
	private Long id;

	@Column(name = "message")
	private String message;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts", columnDefinition = "timestamp")
	private String createdTs;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticket_id")
	private SupportTicket supportTicket;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}
}
