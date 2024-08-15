package com.tucyticket.app.model;

import java.sql.Date;

public class Event {
	private Integer idEvent;
	private String name;
	private String type;
	private Date date;
	private String location;
	private float price;
	
	public Integer getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		location = location;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Event [idEvent=" + idEvent + ", name=" + name + ", type=" + type + ", date=" + date + ", Location="
				+ location + ", price=" + price + "]";
	}
}
