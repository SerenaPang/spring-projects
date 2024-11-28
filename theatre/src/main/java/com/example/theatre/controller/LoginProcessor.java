package com.example.theatre.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class LoginProcessor {
	public LoginProcessor() {
	}

	public boolean login(String username, String password) {
		boolean loginResult = false;

		System.out.println("LoginProcessor.login()");
		if ("Abby".equals(username) && "ab123".equals(password)) {
			loginResult = true;
		}

		return loginResult;
	}
}
