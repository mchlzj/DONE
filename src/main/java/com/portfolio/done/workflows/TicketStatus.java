package com.portfolio.done.workflows;

public enum TicketStatus {

	CLOSED("closed"),
	OPEN("open"),
	IN_PROGRESS("in progress");

	private final String status;
	
	TicketStatus(String status) {
		this.status = status;
	}
	
}
