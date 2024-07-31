package com.petcoco.app.controller;

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

import com.petcoco.app.dao.JdbcUserDao;
import com.petcoco.app.model.User;

@RestController
public class UserController {
	@Autowired
	private JdbcUserDao jdbcUserDao;
	
	public UserController() {
		System.out.println("UserController.UserController()");
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"Ava", "last_name": "Shili" }' -X POST http://localhost:8080/saveUser
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
		
		// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteUser/1
		@DeleteMapping(path = "/deleteUser/{idUser}")
		public ResponseEntity<User> deleteUser(@PathVariable(name = "idUser") Integer idUser) {
			// Retrieve the resource from the database
			User target = jdbcUserDao.findUserById(idUser);
			// If the resource is not found, return a 404 (not found) status code
			if (target == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Delete the resource
			jdbcUserDao.deleteUser(idUser);
			// Return a 204 (no content) status code
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"Milkyky", "lastName":"Pandida"}' -X PUT http://localhost:8080/updateUser
		@PutMapping(path = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<User> updateUser(@RequestBody User user) {
			// Retrieve the resource from the database
			Integer idUser = user.getId();
			User target = jdbcUserDao.findUserById(idUser);
			// If the resource is not found, return a 404 (not found) status code
			if (target == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Update the resource
			User updatedUser = jdbcUserDao.updateUser(user);
			// Return the updated resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
		}
}
