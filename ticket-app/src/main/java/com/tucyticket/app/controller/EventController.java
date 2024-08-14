package com.tucyticket.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tucyticket.app.dao.JdbcEventDao;
import com.tucyticket.app.model.Event;

@RestController
public class EventController {
	@Autowired
	private JdbcEventDao jdbcEventDao;
	
	public EventController() {
		
	}
	// curl -H 'Content-Type: application/json' -d '{ "idEvent":"1", "name":"Hills", "type":"theater", "date":"2023-01-07"}' -X POST http://localhost:8080/saveEvent
	@PostMapping("/saveEvent{event}")
	public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
		Event eventSaved = jdbcEventDao.saveEvent(event);
		if (eventSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(eventSaved);
	}
	
	// curl -X GET "http://localhost:8080/findEventById?id=1"
	@GetMapping("/findEventById")
	public ResponseEntity<Event> findEventById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("eventController.findById() " + id);
		Event resultEvent = jdbcEventDao.findEventById(id);
		if (resultEvent == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		System.out.println("resultEvent: " + resultEvent.toString());
		return ResponseEntity.status(HttpStatus.OK).body(resultEvent);
	}
	
	// curl -X GET "http://localhost:8080/findAllEvents"
		@GetMapping("/findAllEvents")
		public ResponseEntity<List<Event>> findAllEvents() {
			List<Event> events = jdbcEventDao.findAllEvents();
			if (events.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Return the resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(events);
		}
		
		// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteEvent/1
		@DeleteMapping(path = "/deleteEvent/{idEvent}")
		public ResponseEntity<Event> deleteEvent(@PathVariable(name = "idEvent") Integer idEvent) {
			// Retrieve the resource from the database
			Event target = jdbcEventDao.findEventById(idEvent);
			// If the resource is not found, return a 404 (not found) status code
			if (target == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Delete the resource
			jdbcEventDao.deleteEvent(idEvent);
			// Return a 204 (no content) status code
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		// curl -H 'Content-Type: application/json' -d '{"idEvent":"1", "name":"Hillsdale", "type":"theater", "date":"2023-03-06"}' -X PUT http://localhost:8080/updateEvent
		@PutMapping(path = "/updateEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
			// Retrieve the resource from the database
			Integer idEvent = event.getIdEvent();
			Event target = jdbcEventDao.findEventById(idEvent);
			// If the resource is not found, return a 404 (not found) status code
			if (target == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Update the resource
			Event updatedEvent = jdbcEventDao.updateEvent(event);
			// Return the updated resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(updatedEvent);
		}
}