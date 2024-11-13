package com.example.pet_house.web;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pet_house.model.Cat;

@Controller
public class MainController {
	private final CatService catService;

	public MainController(CatService catService) {
		this.catService = catService;
	}
	
	@RequestMapping("/home")
	public String home(Model page) {
		page.addAttribute("username", "Kity");
		page.addAttribute("color", "blue");
		return "home.html";
	}
	
	// curl -X GET "http://localhost:8080/viewCats"
	@GetMapping("/cats")
	public String viewCats(Model model) {
		var cats = catService.findAll();
		model.addAttribute("cats", cats);
		return "cats.html";
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"6", "name":"Bilibii", "age": "1", "breed":"Calico", "description":"Available" }' -X POST http://localhost:8080/addCat
	@PostMapping("/cats")
	public String addCat(@RequestParam int id, @RequestParam String name, int age, String breed, String description, Model model) {
		Cat c = new Cat();
		c.setId(id);
		c.setName(name);
		c.setAge(age);
		c.setBreed(breed);
		c.setDescription(description);
		catService.addCat(c);
		
		var cats = catService.findAll();
		model.addAttribute("cats", cats);
		return "cats.html";
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"6", "name":"Bilibii", "age": "1", "breed":"Calico", "description":"Available" }' -X PUT http://localhost:8080/updateCat
//	@PutMapping("/update/{id}")
//	public String updateCat(@RequestBody Cat cat, @PathVariable final String id) {
//		
//		// Update the resource
//		catService.updateCat(cat);
//		//catService.addCat(cat);
//		return "cats.html";
//	}
	
//	@PutMapping("cat/{id}")
//	public String updateCat(@PathVariable String id, @RequestBody Cat cat) {
//		//Cat currentCat= catService.findCatById(id);
//	   catService.updateCat(cat);
//	   //catService.addCat(cat);
//		return "cats.html";
//	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public String updateCat(@PathVariable String id, @RequestParam String name, int age, String breed, String description, Model model){
		int catId = Integer.parseInt(id);
	    // Cat currentCat= catService.findCatById(catId);
		
		Cat c = new Cat();
		c.setId(catId);
		c.setName(name);
		c.setAge(age);
		c.setBreed(breed);
		c.setDescription(description);
		catService.addCat(c);
	    catService.updateCat(c);
	
	    var cats = catService.findAll();
		model.addAttribute("cats", cats);
		return "cats.html";
	}
}