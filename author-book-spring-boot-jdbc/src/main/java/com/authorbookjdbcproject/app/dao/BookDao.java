package com.authorbookjdbcproject.app.dao;

import java.util.List;

import com.authorbookjdbcproject.app.model.Book;

public interface BookDao {
	public Book saveBook(Integer authorId, Book book);
	public Book findBookById(Integer id);
	public List<Book> findAllBooks();
	public Book deleteBookById(Integer idBook);
	public Book updateBook(Integer authorId, Book book);
}
