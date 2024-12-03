package com.example.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.theatre.model.Theater;

/**
 * This class does save user, update user, delete user, find all users in the
 * website.
 */
@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private TheaterService theaterService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private LoggedUserManagementService loggedUserManagementService;


	@GetMapping("/home")
	public String home(@RequestParam(required = false) String logout, Model model) {
		if (logout != null) {
			loggedUserManagementService.setUsername(null);
		}

		String username = loggedUserManagementService.getUsername();

		if (username == null) {
			return "redirect:/";
		}

		model.addAttribute("username", username);
		return "home.html";
	}

	@GetMapping("/")
	public String loginGet() {
		return "login.html";
	}

	// redirect the user to the theater page after login
	@PostMapping("/")
	public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
		boolean loggedIn = userService.login(username, password);

		if (loggedIn) {
			loggedUserManagementService.setUsername(username);
			return "redirect:/theater";
		}

		model.addAttribute("message", "Login failed!");
		return "login.html";
	}

	@RequestMapping("/home")
	public String home(Model page) {
		page.addAttribute("username", "Kity");
		page.addAttribute("color", "blue");
		return "home.html";
	}

	@RequestMapping("/theater")
	public String theater(Model page) {
		page.addAttribute("username", "Kity");
		page.addAttribute("color", "blue");
		return "theater.html";
	}
	
	@GetMapping("/theater")
	public String viewTheaters(Model model) {
		var theaters = theaterService.findAll();
		model.addAttribute("theaters", theaters);
		return "theater.html";
	}

	@PostMapping("/findtheaterbyzipcode")
	public String findTheaterByZip(@RequestParam String zipcode, Model model) {
		Theater theater = theaterService.findTheaterByZipcode(zipcode);
		model.addAttribute("theater", theater);
		System.out.println(theater.toString());
		return "findtheaterbyzipcode.html";
	}

	@RequestMapping("/movies")
	public String movies(Model model) {
		var movies =  movieService.findAll();
		model.addAttribute("movies", movies);
		return "movies.html";
	}

	@RequestMapping("/food")
	public String food(Model model) {
//		var foods = 
//		model.addAttribute("foods", foods);
		return "food.html";
	}

	@RequestMapping("/tickets")
	public String tickets(Model page) {
		page.addAttribute("username", "Kity");
		page.addAttribute("color", "blue");
		return "tickets.html";
	}
}
