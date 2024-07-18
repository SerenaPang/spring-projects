package com.authorbookproject.app.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.authorbookproject.app.model.Author;
import com.authorbookproject.app.model.Book;

@Repository
public class BookRepository {
	List<Book> books = new ArrayList<>();
	
	public BookRepository() {
		System.out.println("BookRepository.BookRepository()");
	}
	
	public Book saveBook(Book book) {
		System.out.println("BookRepository.saveBook()");
		books.add(book);
		return book;
	}
	
	public Book findBookById(Integer id) {
		Book resultBook = null;
		for (int i = 0; i < books.size(); i++) {
			if (id == books.get(i).getId()) {
				resultBook = books.get(i);
				break;
			}
		}
		return resultBook;
	}
	
	public List<Book> findAllBooks() {
		return books;
	}
	

}
