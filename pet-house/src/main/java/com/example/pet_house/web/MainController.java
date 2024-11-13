package com.example.pet_house.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		//jdbcCatDao.findAllCats();
		return "cats.html";
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"6", "name":"Bilibii",
		// "age": "1", "breed":"Calico", "description":"Available" }' -X POST
		// http://localhost:8080/addCat
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
		
		//jdbcCatDao.saveCat(c);
		return "cats.html";
	}
}