package com.tucybank.app.model;

public class Cilent {
	private String name;
	private String lastName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Cilent [name=" + name + ", lastName=" + lastName + "]";
	}
}
