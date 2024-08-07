package com.tucybank.app.model;

public class Client {
	private int idCilent;
	private int idAccount;
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
	public int getIdCilent() {
		return idCilent;
	}
	public void setIdCilent(int idCilent) {
		this.idCilent = idCilent;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	@Override
	public String toString() {
		return "Client [idCilent=" + idCilent + ", idAccount=" + idAccount + ", name=" + name + ", lastName=" + lastName
				+ "]";
	}
}
