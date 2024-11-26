package com.example.theatre.model;

public class Theater {
	private int id;
	private String zipcode;
	private String city;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Theater [id=" + id + ", zipcode=" + zipcode + ", city=" + city + "]";
	}
}
