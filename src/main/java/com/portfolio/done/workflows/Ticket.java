package com.portfolio.done.workflows;

import com.portfolio.done.appuser.AppUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public abstract class Ticket {

	private Long id;
	private String title;
	private String description;
	private TicketStatus ticketStatus;
	private java.util.List<AppUser> assignedTo;
}
