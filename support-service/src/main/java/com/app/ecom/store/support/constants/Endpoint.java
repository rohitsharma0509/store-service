package com.app.ecom.store.support.constants;

public class Endpoint {
	
	private Endpoint() {
		
	}

	public static final String TICKET = "/ticket";
	public static final String GET_TICKET = TICKET+"/{id}";
	public static final String COUNT_TICKET = "/countTicket";
	
	public static final String UNCLOSED_TICKET = "/unclosedTicket";
	public static final String COUNT_UNCLOSED_TICKET = "/countUnclosedTicket";
	public static final String TICKET_STATUS = "/ticket/status";
	public static final String TICKET_PRIORITY = "/ticket/priority";
	
	public static final String TICKET_ASSIGNMENT_STRATEGY = "/ticketAssignmentStrategy";
}
