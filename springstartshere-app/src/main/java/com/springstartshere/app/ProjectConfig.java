package com.springstartshere.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "test")
public class ProjectConfig {

	@Bean
	Parrot parrot1() {
		var p = new Parrot();
		p.setName("Koko");
		return p;
	}

	@Bean(name = "miki")
	Parrot parrot2() {
		var p = new Parrot();
		p.setName("Miki");
		return p;
	}

	@Bean
	Parrot parrot3() {
		var p = new Parrot();
		p.setName("Riki");
		return p;
	}

	@Bean
	String hello() {
		return "Hello";
	}

	@Bean
	Integer ten() {
		return Integer.valueOf(10);
	}
}