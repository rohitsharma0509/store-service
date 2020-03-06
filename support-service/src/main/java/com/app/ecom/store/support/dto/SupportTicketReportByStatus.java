package com.app.ecom.store.support.dto;

import java.util.Map;

import com.app.ecom.store.support.enums.SupportTicketStatus;

public class SupportTicketReportByStatus implements SupportTicketReport {
	
	private Map<SupportTicketStatus, Long> report;

	public Map<SupportTicketStatus, Long> getReport() {
		return report;
	}

	public void setReport(Map<SupportTicketStatus, Long> report) {
		this.report = report;
	}
}
