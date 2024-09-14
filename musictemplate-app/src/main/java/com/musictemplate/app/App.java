package com.musictemplate.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main class to start Spring Boot with spring jdbc template
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		System.out.println("Starting Spring with jdbc template");
		SpringApplication.run(App.class, args);
	}
}

