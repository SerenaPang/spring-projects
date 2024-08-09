package com.tucybank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.tucybank.app.dao.AccountJdbcDao;
import com.tucybank.app.model.Account;

@RestController
public class AccountController {
	@Autowired
	private AccountJdbcDao accountJdbcDao;

	public AccountController() {
		
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "idAcount":"1", "idClient":"1", "totalBalance":"1000" }' -X POST http://localhost:8080/saveAccount/2
	@PostMapping(path = "/saveAccount/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> saveAccount(@PathVariable(name = "idClient") Integer idClient, @RequestBody Account account) {
		// Save the resource to the database
		Account accountSaved = accountJdbcDao.saveAccount(idClient, account);
		if (accountSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(accountSaved);
	}
	
	
}
