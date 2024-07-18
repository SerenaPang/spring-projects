package com.authorbookproject.app.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.authorbookproject.app.controller.AuthorController;
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
	
	public Book saveBook(Integer idAuthor, Book book) {
		System.out.println("BookController.saveBook() idAuthor " + idAuthor);
		for (Author author : AuthorRepository.authors) {
			if (author.getId() == idAuthor) {
				author.getBooks().add(book);
				return author;
			}
		}
		return book;
	}
	
	public Book deleteByBookId(Integer idBook) {
		Book target = null;
		for (int i = 0; i < books.size(); i++) {
			if (idBook == books.get(i).getId()) {
				target = books.get(i);
				System.out.println("Deleting " + target.toString());
				books.remove(i);
				break;
			}
		}
		return target;
	}
	
	public Book updateBook(Book book) {
		Book target = book;
		for (int i = 0; i < books.size(); i++) {
			if (target.getId() == books.get(i).getId()) {
				books.get(i).setName(target.getName());
				books.get(i).setIsbn(target.getIsbn());
				break;
			}
		}
		return book;
	}
}
