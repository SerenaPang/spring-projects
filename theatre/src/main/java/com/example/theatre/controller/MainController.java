package com.example.theatre.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.theatre.model.Showtime;
import com.example.theatre.model.Theater;
import com.example.theatre.model.Ticket;

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
	private FoodService foodService;
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private ShowtimeService showtimeService;
	@Autowired
	private LoggedUserManagementService loggedUserManagementService;
	@Autowired
	private OrderService orderService;

	@GetMapping("/home")
//	@GetMapping("/")
	public String home(@RequestParam(required = false) String logout, Model model) {
		if (logout != null) {
			loggedUserManagementService.setUsername(null);
		}

		String username = loggedUserManagementService.getUsername();

		if (username == null) {
			return "redirect:/";
		}

		model.addAttribute("username", username);
		//return "home.html";
		return "login.html";
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
			model.addAttribute("username", username);
			return "redirect:/movies";
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
	public String theater(Model model) {
		String user = loggedUserManagementService.getUsername();
		model.addAttribute("username", user);
		return "theater.html";
	}
	
	@GetMapping("/theater")
	public String viewTheaters(Model model) {
		var theaters = theaterService.findAll();
		String user = loggedUserManagementService.getUsername();
		model.addAttribute("username", user);
		model.addAttribute("theaters", theaters);
		return "theater.html";
	}

	@PostMapping("/findtheaterbyzipcode")
	public String findTheaterByZip(@RequestParam String zipcode, Model model) {
		Theater theater = theaterService.findTheaterByZipcode(zipcode);
		String user = loggedUserManagementService.getUsername();
		model.addAttribute("username", user);
		model.addAttribute("theater", theater);
		System.out.println("findtheaterbyzipcode " + theater);
		return "findtheaterbyzipcode.html";
	}

	@RequestMapping("/movies")
	public String movies(Model model) {
		var movies =  movieService.findAll();
		String user = loggedUserManagementService.getUsername();
		model.addAttribute("username", user);
		model.addAttribute("movies", movies);
		return "movies.html";
	}

	@RequestMapping("/food")
	public String food(Model model) {
		var foods = foodService.findAll();
		String user = loggedUserManagementService.getUsername();
		model.addAttribute("username", user);
		model.addAttribute("foods", foods);
		return "food.html";
	}
	
	@RequestMapping("/drink")
	public String drink(Model model) {
		var drinks = drinkService.findAll();
		String user = loggedUserManagementService.getUsername();
		model.addAttribute("username", user);
		model.addAttribute("drinks", drinks);
		return "drink.html";
	}
	
	@GetMapping("/showtimes")
	public String showtime(@RequestParam int movieId, Model model) {
		System.out.println("MainController.showtime() movieId " + movieId);
		List<Showtime> showtimes = showtimeService.findShowtimeByMovieId(movieId);
		String user = loggedUserManagementService.getUsername();
		model.addAttribute("username", user);
		model.addAttribute("showtimes", showtimes);
		System.out.println(showtimes);

		return "showtimes.html";
	}
	
	//@PostMapping("/order")
	@GetMapping("/order")
	public String findShowtimeById(@RequestParam int showtimeId, Model model) {
		Showtime showtime = showtimeService.findShowtimeById(showtimeId);
		
		//Theater theater = theaterService.findTheaterByZipcode(showtime.getTheaterId());
//		String user = loggedUserManagementService.getUsername();
//		model.addAttribute("username", user);
		model.addAttribute("showtime", showtime);
		System.out.println(showtime);
		return "order.html";
		//return "redirect:/tickets";
	}

	//@RequestMapping("/tickets")
	@GetMapping("/tickets")
	public String tickets(Model model) {
		String user = loggedUserManagementService.getUsername();
		
		model.addAttribute("username", user);
		return "tickets.html";
	}
	
	@PostMapping("/tickets")
	public String buyTickets(@RequestParam int quantity, Model model) {
		
//				ticketService.setUserId();
//				ticketService.setShowtime();
//				ticketService.setMovieId();
//				ticketService.setQuantity();
//		
//		String user = loggedUserManagementService.getUsername();
//		model.addAttribute("username", user);
//		model.addAttribute("tickets", tickets);
//		System.out.println("findtheaterbyzipcode " + tickets);
		return "tickets.html";
	}
}
