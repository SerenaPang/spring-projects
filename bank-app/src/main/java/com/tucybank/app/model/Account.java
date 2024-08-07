package com.tucybank.app.model;

import java.util.List;

public class Account {
	private int idAcount;
	private int idClient;
	private float totalBalance;
	List<Activity> activities;
	
	public Account() {
		
	}
	
	public Account(List<Activity> activities){
		this.activities = activities;
	}
	public float getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(float totalBalance) {
		this.totalBalance = totalBalance;
	}

	public float getAmount() {
		return totalBalance;
	}
	public void setAmount(float amount) {
		this.totalBalance = amount;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public float deposit(float amount) {
		totalBalance = totalBalance + amount;
		return totalBalance;	
	}
	
	public float withdraw(float amount) {
		totalBalance = totalBalance - amount;
		return totalBalance;
	}
	public int getIdAcount() {
		return idAcount;
	}
	public void setIdAcount(int idAcount) {
		this.idAcount = idAcount;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	@Override
	public String toString() {
		return "Account [idAcount=" + idAcount + ", idClient=" + idClient + ", totalBalance=" + totalBalance
				+ ", activities=" + activities + "]";
	}
}
