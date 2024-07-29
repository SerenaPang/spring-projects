package com.authorbookjdbcproject.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authorbookjdbcproject.app.dao.BookJdbcDao;
import com.authorbookjdbcproject.app.model.Book;

@RestController
public class BookController {

	@Autowired
	private BookJdbcDao bookDao;

	public BookController() {

	}

	// curl -X GET "http://localhost:8080/findBookById?id=1"
	@GetMapping("/findBookById")
	public ResponseEntity<Book> findBookById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("BookController.findById() " + id);
		Book resultBook = bookDao.findBookById(id);
		if (resultBook == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(resultBook);
	}

	// curl -X GET "http://localhost:8080/findAllBooks"
	@GetMapping("/findAllBooks")
	public ResponseEntity<List<Book>> findAllBooks() {
		List<Book> books = bookDao.findAllBooks();
		if (books == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(books);
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"4", "name":"Pink",
	// "isbn":"cvi-wcd56byd-23" }' -X POST http://localhost:8080/saveBook/3
	@PostMapping(path = "/saveBook/{idAuthor}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> saveBook(@PathVariable(name = "idAuthor") Integer idAuthor, @RequestBody Book book) {
		// Save the resource to the database
		Book bookSaved = bookDao.saveBook(idAuthor, book);
		if (bookSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(bookSaved);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteByBookId/1
	@DeleteMapping(path = "/deleteByBookId/{idBook}")
	public ResponseEntity<Book> deleteAuthor(@PathVariable(name = "idBook") Integer idBook) {
		// Retrieve the resource from the database
		Book target = bookDao.findBookById(idBook);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Delete the resource
		bookDao.deleteBookById(idBook);
		// Return a 204 (no content) status code
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"2", "name":"Black",
	// "isbn":"he-jfv56we-v67"}' -X PUT http://localhost:8080/updateBook
	@PutMapping(path = "/updateBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> updateResource(@RequestBody Book book) {
		// Retrieve the resource from the database
		Integer idBook = book.getId();
		Book target = bookDao.findBookById(idBook);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Update the resource
		Book updatedBook = bookDao.updateBook(book);
		// Return the updated resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
	}
}
