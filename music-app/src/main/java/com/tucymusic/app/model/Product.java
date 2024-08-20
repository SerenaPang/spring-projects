package com.tucymusic.app.model;

import java.math.BigDecimal;
import java.util.Date;


public class Product {
	private int id;
	private int productType;
	private int genreId;
	private BigDecimal price;
	//private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productType=" + productType + ", genreId=" + genreId + ", price=" + price + "]";
	}
}
