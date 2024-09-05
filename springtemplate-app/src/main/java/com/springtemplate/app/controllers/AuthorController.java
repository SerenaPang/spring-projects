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

	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"AAAB"}' -X POST http://localhost:8080/saveAuthor
	@PostMapping("/saveAuthor{author}")
	public void saveAuthor(@RequestBody Author author) {
		authorDao.save(author);
	}

	// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteAuthor/1
	@DeleteMapping(path = "/deleteAuthor/{idAuthor}")
	public void deleteAuthor(@PathVariable(name = "idAuthor") Integer idAuthor) {
		authorDao.delete(idAuthor);

	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"MMMMM"}' -X PUT http://localhost:8080/updateAuthor
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

}
