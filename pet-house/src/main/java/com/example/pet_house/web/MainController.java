package com.example.pet_house.web;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	@GetMapping("/update/{id}")
	public String updateCat(@PathVariable final String id) {
		int catId = Integer.parseInt(id);
		Cat currentCat= catService.findCatById(catId);
		// Update the resource
		catService.updateCat(currentCat);
		
		return "update.html";
	}
	
	@PostMapping("/update")
	public String updateCat(@RequestParam int id, @RequestParam String name, int age, String breed, String description) {		
		// Update the resource
		Cat c = new Cat();
		c.setId(id);
		c.setName(name);
		c.setAge(age);
		c.setBreed(breed);
		c.setDescription(description);
		
		catService.updateCat(c);
		System.out.println("updated cat: " + c.toString());
		return "update.html";
	}
		

}