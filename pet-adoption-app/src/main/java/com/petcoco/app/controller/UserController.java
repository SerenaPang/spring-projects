package com.petcoco.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petcoco.app.dao.JdbcUserDao;
import com.petcoco.app.model.User;

@RestController
public class UserController {
	@Autowired
	private JdbcUserDao jdbcUserDao;
	
	public UserController() {
		System.out.println("UserController.UserController()");
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"Ava", "last_name": "Shili" }' -X POST http://localhost:8080/saveAuthor
	@PostMapping("/saveUser{user}")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User userSaved = jdbcUserDao.saveUser(user);
		if (userSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
	}
	
	// curl -X GET "http://localhost:8080/findUserById?id=1"
	@GetMapping("/findUserById")
	public ResponseEntity<User> findUserById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		System.out.println("userController.findById() " + id);
		User resultUser = jdbcUserDao.findUserById(id);
		if (resultUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		System.out.println("resultUser: " + resultUser.toString());
		return ResponseEntity.status(HttpStatus.OK).body(resultUser);
	}
	
	// curl -X GET "http://localhost:8080/findAllUsers"
		@GetMapping("/findAllUsers")
		public ResponseEntity<List<User>> findAllUsers() {
			List<User> users = jdbcUserDao.findAllUsers();
			if (users.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Return the resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
}
