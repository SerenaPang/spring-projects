package com.tucymusic.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tucymusic.app.dao.jdbc.UserDaoImpl;
import com.tucymusic.app.model.User;

@RestController
public class UserController {
	@Autowired
	private UserDaoImpl jdbcUserDao;
	
	public UserController() {
		System.out.println("UserController.UserController()");
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"Ava", "last_name": "Shili" }' -X POST http://localhost:8080/create
	@PostMapping("/create{user}")
	public ResponseEntity<User> create(@RequestBody User user) {
		jdbcUserDao.create(user);
		if (user == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	// curl -X GET "http://localhost:8080/findById?id=1"
	@GetMapping("/findById")
	public ResponseEntity<User> findById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("userController.findById() " + id);
		User resultUser = jdbcUserDao.findById(id);
		if (resultUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		System.out.println("resultUser: " + resultUser.toString());
		return ResponseEntity.status(HttpStatus.OK).body(resultUser);
	}
	
	// curl -X GET "http://localhost:8080/findAll"
		@GetMapping("/findAll")
		public ResponseEntity<List<User>> findAll() {
			List<User> users = jdbcUserDao.findAll();
			if (users.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Return the resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}

		// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"Milkyky"}' -X PUT http://localhost:8080/update
		@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<User> update(@RequestBody User user) {
			// Retrieve the resource from the database
			Integer idUser = user.getId();
			User target = jdbcUserDao.findById(idUser);
			// If the resource is not found, return a 404 (not found) status code
			if (target == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Update the resource
			jdbcUserDao.update(user);
			// Return the updated resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
}
