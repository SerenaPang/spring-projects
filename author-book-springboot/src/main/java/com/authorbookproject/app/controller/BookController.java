package com.authorbookproject.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.app.model.Book;

@RestController
public class BookController {
	private List<Book> books = new ArrayList<>();
	
	public BookController() {
		Book blue = new Book(0, "Blue", "fyg-y3g245-dg");
		Book red = new Book(1, "Red", "bfd-io58qgh-ds");
		Book yellow = new Book(2, "Yellow", "yfu-4g84f-re");
		books.add(blue);
		books.add(red);
		books.add(yellow);
	}
	
	// curl -X GET "http://localhost:8080/findBookById?id=1"
	@GetMapping("/findBookById")
	public Book findBookById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("BookController.findById() " + id);
		Book resultBook = null;
		for (int i = 0; i < books.size(); i++) {
			if (id == books.get(i).getId()) {
				resultBook = books.get(i);
				break;
			}
		}
		return resultBook;
	}
	
	// curl -X GET "http://localhost:8080/findAllBooks" 
	@GetMapping("/findAllBooks")
	public List<Book> findAllBooks() {
		return books;
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"3", "name":"Pink", "isbn":"cvi-wcd56byd-23" }' -X POST http://localhost:8080/saveBook
	@PostMapping(path = "/saveBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveBook(@RequestBody Book book) {
		books.add(book);
		System.out.println("The list of books is " + books);
	}

	// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteByBookId/1
	@DeleteMapping(path = "/deleteByBookId/{idBook}")  
	public Book deleteByBookId(@PathVariable(name = "idBook") Integer idBook) {
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
