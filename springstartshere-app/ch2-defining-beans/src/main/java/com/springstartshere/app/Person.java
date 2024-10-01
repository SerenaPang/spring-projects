package com.springstartshere.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
//	private String name;
//	private Parrot parrot;

	private String name = "Ella";
	@Autowired
	private Parrot parrot;

	public Person(Parrot parrot) {
		this.parrot = parrot; 
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parrot getParrot() {
		return parrot;
	}

	public void setParrot(Parrot parrot) {
		this.parrot = parrot;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", parrot=" + parrot + "]";
	}
}
