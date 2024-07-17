package com.example.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private List<Greeting> greetings = new ArrayList<>();
	
	
	// curl -X GET "http://localhost:8080/greeting?name=tu"
	// http://localhost:8080/greeting?name=Silver
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Calling greeting with parameter " + name);
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	// curl -X GET  "http://localhost:8080/goodnight?n=Silver"
	// http://localhost:8080/goodnight?n=Silver
	@GetMapping("/goodnight")
	public String sayBye(@RequestParam(value = "n", defaultValue = "Everybody") String msg) {
		System.out.println("Calling goodnight with parameter " + msg);
		return "Good Night " + msg;
	}
	
	//  curl -H 'Content-Type: application/json' -d '{ "id":"24", "content":"Helly my love"}' -X POST http://localhost:8080/savegreeting
	@PostMapping(path = "/savegreeting",
			   consumes = MediaType.APPLICATION_JSON_VALUE,
			   produces = MediaType.APPLICATION_JSON_VALUE)
	public String savegreeting(@RequestBody Greeting msg) {
		
		greetings.add(msg);
		
		System.out.println("The list of greetings is " + greetings);
		
		return "Saved Greeting " + msg;
	}	
	
}
