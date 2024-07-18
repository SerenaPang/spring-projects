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

import com.authorbookproject.app.model.Author;
import com.authorbookproject.app.model.Book;

@RestController
public class AuthorController {
	private List<Book> peachBooks = new ArrayList<>();
	private List<Book> melonBooks = new ArrayList<>();
	private List<Book> cheeryBooks = new ArrayList<>();
	private List<Book> appleBooks = new ArrayList<>();
	public static List<Author> authors = new ArrayList<>();

	public AuthorController() {
		Author peach = new Author(0, "Haruki Murakami", peachBooks);
		Author melon = new Author(1, "Sun Zi", melonBooks);
		Author cheery = new Author(2, "Milan Kundera", cheeryBooks);
		Author apple = new Author(3, "Natsuo Kirino", appleBooks);
		authors.add(apple);
		authors.add(cheery);
		authors.add(peach);
		authors.add(melon);
	}

	// curl -X GET "http://localhost:8080/findAuthorById?id=1"
	@GetMapping("/findAuthorById")
	public Author findAuthorById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("AuthorController.findById() " + id);
		Author resultAuthor = null;
		for (int i = 0; i < authors.size(); i++) {
			if (id == authors.get(i).getId()) {
				resultAuthor = authors.get(i);
				break;
			}
		}
		return resultAuthor;
	}

	// curl -X GET "http://localhost:8080/findAllAuthors"
	@GetMapping("/findAllAuthors")
	public List<Author> findAllAuthors() {
		return authors;
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"AAAB",
	// "books":[{"id":"3", "name":"Pink", "isbn":"cvi-wcd56byd-23"}, {"id":"2",
	// "name":"Black", "isbn":"he-jfv56we-v67"}] }' -X POST
	// http://localhost:8080/saveAuthor
	@PostMapping(path = "/saveAuthor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Author saveAuthor(@RequestBody Author author) {
		authors.add(author);
		System.out.println("The list of authors is " + authors);
		return author;
	}

	// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteByAuthorId/1
	@DeleteMapping(path = "/deleteByAuthorId/{idAuthor}")
	public Author deleteByAuthorId(@PathVariable(name = "idAuthor") Integer idAuthor) {
		Author target = null;
		for (int i = 0; i < authors.size(); i++) {
			if (idAuthor == authors.get(i).getId()) {
				target = authors.get(i);
				System.out.println("Deleting " + target.toString());
				authors.remove(i);
				break;
			}
		}
		return target;
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"MMMMM", "books":[{"id":"3", "name":"Pink", "isbn":"cvi-wcd56byd-23"}, {"id":"2", "name":"Black", "isbn":"he-jfv56we-v67"}] }' -X PUT http://localhost:8080/updateAuthor
		@PutMapping(path = "/updateAuthor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public Author updateAuthor(@RequestBody Author author) {
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

}
