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

import com.mycompany.app.model.Author;
import com.mycompany.app.model.Book;

@RestController
public class AuthorController {
	private List<Book> peachBooks = new ArrayList<>();
	private List<Book> melonBooks = new ArrayList<>();
	private List<Book> cheeryBooks = new ArrayList<>();
	private List<Book> appleBooks = new ArrayList<>();
	private List<Author> authors = new ArrayList<>();
	
	public AuthorController() {
		Author peach = new Author(0, "Pink Peach", peachBooks); 
		Author melon = new Author(1, "Yellow Melon", melonBooks); 
		Author cheery = new Author(2, "Red Cheery", cheeryBooks); 
		Author apple = new Author(3, "Green Apple", appleBooks); 	
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
	

}
