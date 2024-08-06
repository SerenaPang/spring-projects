package com.tucybank.app.model;

public class Activity {
	private String type;
	private float amount;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Activity [type=" + type + ", amount=" + amount + "]";
	}
}
