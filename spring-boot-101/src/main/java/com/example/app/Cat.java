package com.example.app;

//public record Cat(int id, String name) { }

public class Cat{
	int id;
	String name;
	
	public Cat(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}