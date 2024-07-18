package com.authorbookproject.app.controller;

import java.util.ArrayList;
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

import com.authorbookproject.app.model.Author;
import com.authorbookproject.app.model.Book;
import com.authorbookproject.app.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	public BookController() {
		
	}
	
	// curl -X GET "http://localhost:8080/findBookById?id=1"
	@GetMapping("/findBookById")
	public Book findBookById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("BookController.findById() " + id);
		Book resultBook = bookRepository.findBookById(id);
		return resultBook;
	}
	
	// curl -X GET "http://localhost:8080/findAllBooks" 
	@GetMapping("/findAllBooks")
	public List<Book> findAllBooks() {
		return bookRepository.findAllBooks();
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"4", "name":"Pink", "isbn":"cvi-wcd56byd-23" }' -X POST http://localhost:8080/saveBook/3
	@PostMapping(path = "/saveBook/{idAuthor}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Author saveBook(@PathVariable(name = "idAuthor") Integer idAuthor, @RequestBody Book book) {
		System.out.println("BookController.saveBook() idAuthor " + idAuthor);
		for (Author author : AuthorController.authors) {
			if (author.getId() == idAuthor) {
				author.getBooks().add(book);
				return author;
			}
		}
		return book;
	}

	// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteByBookId/1
	@DeleteMapping(path = "/deleteByBookId/{idBook}")  
	public Book deleteByBookId(@PathVariable(name = "idBook") Integer idBook) {
		Book target = bookRepository.deleteByBookId(idBook);
		return target;
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"2", "name":"Black", "isbn":"he-jfv56we-v67"}' -X PUT http://localhost:8080/updateBook
	@PutMapping(path = "/updateBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book updateBook(@RequestBody Book book) {
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
