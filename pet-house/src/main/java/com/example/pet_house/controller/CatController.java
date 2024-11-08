package com.example.pet_house.controller;

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

import com.example.pet_house.dao.JdbcCatDao;
import com.example.pet_house.model.Cat;

@RestController
public class CatController {
	@Autowired
	private JdbcCatDao jdbcCatDao;

	public CatController() {
		System.out.println("Cat controller");
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"6", "name":"Bilibii",
	// "age": "1", "breed":"Calico", "description":"Available" }' -X POST
	// http://localhost:8080/saveCat
	@PostMapping("/saveCat{cat}")
	public ResponseEntity<Cat> savePet(@RequestBody Cat cat) {
		Cat petSaved = jdbcCatDao.saveCat(cat);
		if (petSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(petSaved);
	}
	
	// curl -X GET "http://localhost:8080/findAllCats"
		@GetMapping("/findAllCats")
		public ResponseEntity<List<Cat>> findAllCats() {
			List<Cat> cats = jdbcCatDao.findAllCats();
			if (cats.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Return the resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(cats);
		}
}
