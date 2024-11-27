package com.example.theatre.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {
 
  private String username;
  private String password;
 
  public boolean login() {
    String username = this.getUsername();
    String password = this.getPassword();
 
    if ("Abby".equals(username) && "password".equals(password)) {
      return true;
    } else {
      return false;
    }
  }

private String getPassword() {
	return password;
}

private String getUsername() {
	return username;
}
 
}
