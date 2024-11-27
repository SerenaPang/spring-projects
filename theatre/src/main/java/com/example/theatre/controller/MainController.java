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

	public MainController(UserService userService, LoggedUserManagementService loggedUserManagementService) {
		this.userService = userService;
		this.loggedUserManagementService = loggedUserManagementService;
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

	@PostMapping("/")
	public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
		boolean loggedIn = false;
		if (loggedIn) {
			model.addAttribute("message", "You are now logged in.");
		} else {
			model.addAttribute("message", "Login failed");
		}
		return "login.html";
	}

//	//redirect the user to the main page after login
//	@PostMapping("/")
//	public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
//		loginProcessor.setUsername(username);
//		loginProcessor.setPassword(password);
//		boolean loggedIn = loginProcessor.login();
//
//		if (loggedIn) {
//			return "redirect:/main";
//		}
//
//		model.addAttribute("message", "Login failed!");
//		return "login.html";
//	}
}
