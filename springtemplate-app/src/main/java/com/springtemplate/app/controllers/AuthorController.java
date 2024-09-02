package com.springtemplate.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springtemplate.app.jdbc.AuthorDao;
import com.springtemplate.app.model.Author;

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
}
