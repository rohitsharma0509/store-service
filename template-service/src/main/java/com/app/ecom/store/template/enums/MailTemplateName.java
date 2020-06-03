package com.app.ecom.store.template.enums;

public enum MailTemplateName {
	PSWRD_CHANGED_TEMPLATE("pswrdChangedTmpl", "Did you changed your password?"),
	PSWRD_RESET_TEMPLATE("pswrdResetTmpl", "Reset your password"),
	SUPPORT_TICKET_ASSIGNED_TEMPLATE("supportTicketAssignedTmpl", "Support Ticket Assigned"),
	USER_REGISTERED_TEMPLATE("userRegisteredTmpl", "Registration Confirmation");
	
	private String name;
	private String subject;
	
	private MailTemplateName(String name, String subject) {
		this.name = name;
		this.subject = subject;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSubject() {
		return subject;
	}

}
