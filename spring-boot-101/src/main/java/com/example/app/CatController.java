package com.example.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.Cat;

// Cat id and name.
@RestController
public class CatController {
// Declare list of cats
	private List<Cat> cats = new ArrayList<>();
	private static final String findId = "Hello, find id : %d!";
	private final AtomicLong counter = new AtomicLong();
	private static final int defaultId = 0;

	public CatController() {
		Cat silver = new Cat(0, "Silver");
		Cat diana = new Cat(1, "Diana");
		cats.add(silver);
		cats.add(diana);
	}

	// curl -X GET "http://localhost:8080/findById?id=23"
	@GetMapping("/findById")
	public Cat findById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		Cat resultCat = null;
		for (int i = 0; i < cats.size(); i++) {
			if (id == cats.get(i).getId()) {
				resultCat = cats.get(i);
				break;
			}
		}
		return resultCat;
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"24", "name":"Tucy"}' -X POST http://localhost:8080/savecat
	@PostMapping(path = "/savecat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Cat save(@RequestBody Cat cat) {
		cats.add(cat);
		System.out.println("The list of cats is " + cats);
		return cat;
	}

	// curl -H 'Content-Type: application/json' -d '{ "id":"24", "name":"Tucita"}' -X PUT http://localhost:8080/updatecat
	@PutMapping(path = "/updatecat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Cat update(@RequestBody Cat cat) {
		System.out.println("updating " + cat.name);
		Cat resultCat = null;
		for (int i = 0; i < cats.size(); i++) {
			if (cat.id == cats.get(i).getId()) {
				cats.get(i).name = cat.name;
				break;
			}	
		}

		return resultCat;
	}
	
	// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteById/1
	@DeleteMapping(path = "/deleteById/{idCat}") 
	public Cat deleteById(@PathVariable(name = "idCat") Integer idCat) {
		System.out.println("Deleting " + idCat);
		return null;
	}

	// curl -X GET "http://localhost:8080/findAll"
	@GetMapping("/findAll")
	public List<Cat> findAll() {
		// @RequestParam(value = "allcats", defaultValue = "")
		return cats;
	}
}
