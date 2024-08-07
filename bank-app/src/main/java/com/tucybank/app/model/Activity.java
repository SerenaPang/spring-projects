package com.tucybank.app.model;

import java.sql.Date;

public class Activity {
	private int idActivity;
	private int idAccount;
	private Date date;
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
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Activity [idActivity=" + idActivity + ", idAccount=" + idAccount + ", date=" + date + ", type=" + type
				+ ", amount=" + amount + "]";
	}
}
