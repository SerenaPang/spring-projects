package com.authorbookproject.app.controller;

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

import com.authorbookproject.app.model.Book;
import com.authorbookproject.app.repository.AuthorRepository;

@RestController
public class BookController {

	@Autowired
	private AuthorRepository authorRepository;
	
	public BookController() {
		
	}
	
	// curl -X GET "http://localhost:8080/findBookById?id=1"
//	@GetMapping("/findBookById")
//	public Book findBookById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
//		System.out.println("BookController.findById() " + id);
//		Book resultBook = authorRepository.findBookById(id);
//		return resultBook;
//	}
	
	// curl -X GET "http://localhost:8080/findBookById?id=1"
	@GetMapping("/findBookById")
	public ResponseEntity<Book> findBookById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("BookController.findById() " + id);
		Book resultBook = authorRepository.findBookById(id);
		 if (resultBook == null) {
		        return ResponseEntity
		                .status(HttpStatus.NOT_FOUND)
		                .build();
		    }
		    // Return the resource with a 200 (OK) status code
		    return ResponseEntity
		            .status(HttpStatus.OK)
		            .body(resultBook);
	}

	// curl -X GET "http://localhost:8080/findAllBooks" 
	@SuppressWarnings("unchecked")
	@GetMapping("/findAllBooks")
	public List<Book> findAllBooks() {
		List<Book> books = authorRepository.findAllBooks();
		 if (books == null) {
		        return (List<Book>) ResponseEntity
		                .status(HttpStatus.NOT_FOUND)
		                .build();
		    }
		    // Return the resource with a 200 (OK) status code
		    return (List<Book>) ResponseEntity
		            .status(HttpStatus.OK)
		            .body(books);
	}
	
	
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"4", "name":"Pink", "isbn":"cvi-wcd56byd-23" }' -X POST http://localhost:8080/saveBook/3
	@PostMapping(path = "/saveBook/{idAuthor}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book saveBook(@PathVariable(name = "idAuthor") Integer idAuthor, @RequestBody Book book) {
		System.out.println("BookController.saveBook() idAuthor " + idAuthor);
		authorRepository.saveBook(idAuthor, book);
		return book;
	}

	// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteByBookId/1
	@DeleteMapping(path = "/deleteByBookId/{idBook}")  
	public Book deleteByBookId(@PathVariable(name = "idBook") Integer idBook) {
		Book target = authorRepository.deleteByBookId(idBook);
		return target;
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"2", "name":"Black", "isbn":"he-jfv56we-v67"}' -X PUT http://localhost:8080/updateBook
	@PutMapping(path = "/updateBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book updateBook(@RequestBody Book book) {
		authorRepository.updateBook(book);
		return book;
	}
}
