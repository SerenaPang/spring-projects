package com.authorbookproject.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.authorbookproject.app.model.Author;
import com.authorbookproject.app.model.Book;

@Repository
public class AuthorRepository {
	List<Author> authors = new ArrayList<>();
	List<Book> books = new ArrayList<>();

	public AuthorRepository() {
		System.out.println("AuthorRepository.AuthorRepository()");
	}

	public Author saveAuthor(Author author) {
		System.out.println("AuthorRepository.saveAuthor()");
		authors.add(author);
		return author;
	}

	public Author findAuthorById(Integer id) {
		Author resultAuthor = null;
		for (int i = 0; i < authors.size(); i++) {
			if (id == authors.get(i).getId()) {
				resultAuthor = authors.get(i);
				break;
			}
		}
		return resultAuthor;
	}

	public List<Author> findAllAuthors() {
		System.out.println("AuthorRepository.findAllAuthors()");
		return authors;
	}

	public Author deleteByAuthorId(Integer idAuthor) {
		System.out.println("AuthorRepository.deleteByAuthorId()");
		Author target = null;
		for (int i = 0; i < authors.size(); i++) {
			if (idAuthor == authors.get(i).getId()) {
				target = authors.get(i);
				authors.remove(i);
				break;
			}
		}
		return target;
	}

	public Author updateAuthor(Author author) {
		Author target = author;
		for (int i = 0; i < authors.size(); i++) {
			if (target.getId() == authors.get(i).getId()) {
				authors.get(i).setName(target.getName());
				authors.get(i).setBooks(author.getBooks());
				break;
			}
		}
		return author;
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
	
	public Book saveBook(Integer idAuthor, Book book) {
		System.out.println("BookController.saveBook() idAuthor " + idAuthor);
		for (Author author : authors) {
			if (author.getId() == idAuthor) {
				author.getBooks().add(book);
				break;
			} else {
				return null;		
			}
		}
		return book;
	}
}
