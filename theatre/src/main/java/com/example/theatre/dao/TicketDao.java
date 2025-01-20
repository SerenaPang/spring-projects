package com.example.theatre.dao;

import com.example.theatre.model.Ticket;

public interface TicketDao {
	public Ticket purchaseTicket(int userId, int showtimeId, int quantity);
}
