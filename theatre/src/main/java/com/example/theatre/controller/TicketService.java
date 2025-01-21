package com.example.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.example.theatre.dao.TicketDao;
import com.example.theatre.model.Ticket;

@Service
@ApplicationScope
public class TicketService {
	@Autowired
	private TicketDao ticketDao;
	
	public Ticket purchaseTicket(int userId, int showtimeId, int quantity) {
		return ticketDao.purchaseTicket(userId, showtimeId, quantity);
	}
}
