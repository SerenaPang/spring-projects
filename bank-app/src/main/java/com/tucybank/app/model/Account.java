package com.tucybank.app.model;

import java.util.List;

public class Account {
	private float amount;
	List<Activity> activities;
	public Account(List<Activity> activities){
		this.activities = activities;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	@Override
	public String toString() {
		return "Account [amount=" + amount + ", activities=" + activities + "]";
	}
}
