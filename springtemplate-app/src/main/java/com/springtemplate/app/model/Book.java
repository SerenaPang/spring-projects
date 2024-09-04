package com.springtemplate.app.model;

public class Book {
	private int id;
	private String name;
	private String isbn;
	private int authorId;

	public Book(int id, String name, String isbn, int authorId) {
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.authorId = authorId;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", isbn=" + isbn + ", authorId=" + authorId + "]";
	}
}
