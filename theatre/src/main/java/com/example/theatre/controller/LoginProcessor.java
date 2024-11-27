package com.example.theatre.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {
	LoggedUserManagementService loggedUserManagementService = new LoggedUserManagementService();

	private String username;
	private String password;

	public LoginProcessor(LoggedUserManagementService loggedUserManagementService) {
		this.loggedUserManagementService = loggedUserManagementService;
	}

	public boolean login() {
		String username = this.getUsername();
		String password = this.getPassword();
		boolean loginResult = false;

		if ("Abby".equals(username) && "password".equals(password)) {
			loginResult = true;
		} else {
			loggedUserManagementService.setUsername(username);
		}
		return loginResult;
	}

	private String getPassword() {
		return password;
	}

	private String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
