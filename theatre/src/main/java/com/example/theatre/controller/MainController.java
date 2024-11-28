package com.example.theatre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This class does save user, update user, delete user, find all users in the
 * website.
 */
@Controller
public class MainController {
	private final UserService userService;
	private final LoggedUserManagementService loggedUserManagementService;
	private final LoginProcessor loginProcessor;

	public MainController(UserService userService, LoggedUserManagementService loggedUserManagementService,
			LoginProcessor loginProcessor) {
		this.userService = userService;
		this.loggedUserManagementService = loggedUserManagementService;
		this.loginProcessor = loginProcessor;
	}

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
		boolean loggedIn = loginProcessor.login(username, password);
		System.out.println("MainController.loginPost() " + loggedIn);

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

	@RequestMapping("/findtheaterbyzipcode")
	public String findTheater(Model page) {
		page.addAttribute("username", "Kity");
		page.addAttribute("color", "blue");
		return "findtheaterbyzipcode.html";
	}

	@RequestMapping("/movies")
	public String movies(Model page) {
		page.addAttribute("username", "Kity");
		page.addAttribute("color", "blue");
		return "movies.html";
	}

	@RequestMapping("/food")
	public String food(Model page) {
		page.addAttribute("username", "Kity");
		page.addAttribute("color", "blue");
		return "food.html";
	}

	@RequestMapping("/tickets")
	public String tickets(Model page) {
		page.addAttribute("username", "Kity");
		page.addAttribute("color", "blue");
		return "tickets.html";
	}
}
