package com.tucyticket.app.dao;

import java.util.List;

import com.tucyticket.app.model.Event;

public interface EventDao {
	public Event saveEvent(Event event);
	public Event findEventById(Integer id);
	public List<Event> findAllEvents();
	public Event deleteEvent(Integer idEvent);
	public Event updateEvent(Event event);
}
