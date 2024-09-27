package com.springstartshere.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "test")
public class ProjectConfig {

	@Bean
	public Parrot parrot() {
		Parrot p = new Parrot();
		p.setName("Koko");
		return p;
	}

	//direct wiring
//	@Bean
//	public Person person() {
//		Person p = new Person();
//		p.setName("Ella");
//		//establish relationship btw person and parrot
//		p.setParrot(parrot());
//		return p;
//	}
	
	//injecting bean by parameter
	@Bean
	public Person person(Parrot parrot) {
		Person p = new Person();
		p.setName("Ella");
		//establish relationship btw person and parrot
		p.setParrot(parrot);
		return p;
	}

//
//	@Bean
//	Parrot parrot3() {
//		var p = new Parrot();
//		p.setName("Riki");
//		return p;
//	}
//
//	@Bean
//	String hello() {
//		return "Hello";
//	}
//
//	@Bean
//	Integer ten() {
//		return Integer.valueOf(10);
//	}
}