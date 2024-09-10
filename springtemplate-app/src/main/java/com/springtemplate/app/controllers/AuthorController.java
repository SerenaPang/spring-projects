package com.springtemplate.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springtemplate.app.jdbc.AuthorDao;
import com.springtemplate.app.model.Author;
import com.springtemplate.app.model.Book;

@RestController
public class AuthorController {

	@Autowired
	private AuthorDao authorDao;

	// curl -X GET "http://localhost:8080/findAuthorById?id=2"
	@GetMapping("/findAuthorById")
	public Author findAuthorById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
		System.out.println("AuthorController.findById()");
		Author author = authorDao.findAuthorById(id);
		return author;
	}

	// curl -X GET "http://localhost:8080/findAllAuthors"
	@GetMapping("/findAllAuthors")
	public List<Author> findAllAuthors() {
		List<Author> authors = authorDao.findAllAuthors();
		if (authors.isEmpty()) {
			return null;
		}
		return authors;
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"25", "name":"AAAB"}' -X
	// POST http://localhost:8080/saveAuthor
	@PostMapping("/saveAuthor{author}")
	public void saveAuthor(@RequestBody Author author) {
		authorDao.save(author);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteAuthor/1
	@DeleteMapping(path = "/deleteAuthor/{idAuthor}")
	public void deleteAuthor(@PathVariable(name = "idAuthor") Integer idAuthor) {
		authorDao.delete(idAuthor);

	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"MMMMM"}' -X
	// PUT http://localhost:8080/updateAuthor
	@PutMapping(path = "/updateAuthor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateAuthor(@RequestBody Author author) {
		// Retrieve the resource from the database
		long idAuthor = author.getId();
		Author target = authorDao.findAuthorById(idAuthor);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			System.out.println("no author exist");
		}
		// Update the resource
		authorDao.update(author);
	}

	// curl -X GET "http://localhost:8080/findBookById?id=2"
	@GetMapping("/findBookById")
	public Book findBookById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
		System.out.println("AuthorController.findBookById()");
		Book book = authorDao.findBookById(id);
		return book;
	}

	// curl -X GET "http://localhost:8080/findAllBooks"
	@GetMapping("/findAllBooks")
	public List<Book> findAllBooks() {
		List<Book> books = authorDao.findAllBooks();
		if (books.isEmpty()) {
			return null;
		}
		return books;
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"ABCD",
	// "isbn":"CRFGHJ56Y7 "id_author":"1"}' -X POST http://localhost:8080/save
	@PostMapping("/save{book}")
	public void saveAuthor(@RequestBody Book book) {
		authorDao.save(book);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteBook/1
	@DeleteMapping(path = "/deleteBook/{idBook}")
	public void deleteBook(@PathVariable(name = "idBook") Integer idBook) {
		authorDao.delete(idBook);

	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"MMMMM",
	// "isbn":"45f-gh56-8gh", "id_author":"5"}' -X PUT http://localhost:8080/update
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Book book) {
		// Retrieve the resource from the database
		long idBook = book.getId();
		Book target = authorDao.findBookById(idBook);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			System.out.println("no book exist");
		}
		// Update the resource
		authorDao.update(book);
	}

}
