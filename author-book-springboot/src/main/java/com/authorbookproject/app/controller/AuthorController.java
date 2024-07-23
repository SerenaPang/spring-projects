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

import com.authorbookproject.app.model.Author;
import com.authorbookproject.app.model.Book;
import com.authorbookproject.app.repository.AuthorRepository;


// https://medium.com/@daryl-goh/spring-boot-requestentity-vs-responseentity-requestbody-vs-responsebody-dc808fb0d86c
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html	

// TODO: Return ResponseEntity<> in all the Mapping methods
		
@RestController
public class AuthorController {
	@Autowired
	private AuthorRepository authorRepository;

	public AuthorController() {
	
	}

	// curl -X GET "http://localhost:8080/findAuthorById?id=1"
//	@GetMapping("/findAuthorById")
//	public Author findAuthorById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
//		System.out.println("AuthorController.findById() " + id);
//		Author resultAuthor = authorRepository.findAuthorById(id);
//		return resultAuthor;
//	}
//	
	@GetMapping("/findAuthorById")
	public ResponseEntity<Author> findAuthorById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("BookController.findById() " + id);
		Author resultAuthor = authorRepository.findAuthorById(id);
		 if (resultAuthor == null) {
		        return ResponseEntity
		                .status(HttpStatus.NOT_FOUND)
		                .build();
		    }
		    // Return the resource with a 200 (OK) status code
		    return ResponseEntity
		            .status(HttpStatus.OK)
		            .body(resultAuthor);
	}

	// curl -X GET "http://localhost:8080/findAllAuthors"	
	@SuppressWarnings("unchecked")
	@GetMapping("/findAllAuthors")
	public  List<Author> findAllAuthors() {
		 List<Author> authors = authorRepository.findAllAuthors();
		 if (authors == null) {
		        return ( List<Author> ) ResponseEntity
		                .status(HttpStatus.NOT_FOUND)
		                .build();
		    }
		    // Return the resource with a 200 (OK) status code
		    return ( List<Author> ) ResponseEntity
		            .status(HttpStatus.OK)
		            .body(authors);
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"AAAB", "books":[{"id":"3", "name":"Pink", "isbn":"cvi-wcd56byd-23"}, {"id":"2",  "name":"Black", "isbn":"he-jfv56we-v67"}] }' -X POST http://localhost:8080/saveAuthor
	@PostMapping("/saveAuthor{author}")
	public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
		Author authorSaved = authorRepository.saveAuthor(author);
		if (authorSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(authorSaved);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteByAuthorId/1
	@DeleteMapping(path = "/deleteByAuthorId/{idAuthor}")
	public Author deleteByAuthorId(@PathVariable(name = "idAuthor") Integer idAuthor) {
		Author target = authorRepository.deleteByAuthorId(idAuthor);
		// TODO: if target is null, target.toString() will cause NullPointerException
		System.out.println("Deleting " + target.toString());
		return target;
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"MMMMM",
	// "books":[{"id":"3", "name":"Pink", "isbn":"cvi-wcd56byd-23"}, {"id":"2",
	// "name":"Black", "isbn":"he-jfv56we-v67"}] }' -X PUT
	// http://localhost:8080/updateAuthor
	@PutMapping(path = "/updateAuthor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Author updateAuthor(@RequestBody Author author) {
		authorRepository.updateAuthor(author);
		return author;
	}
}
