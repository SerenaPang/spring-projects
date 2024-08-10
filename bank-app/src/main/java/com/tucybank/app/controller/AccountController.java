package com.tucybank.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tucybank.app.dao.AccountJdbcDao;
import com.tucybank.app.model.Account;

@RestController
public class AccountController {
	@Autowired
	private AccountJdbcDao accountJdbcDao;

	public AccountController() {

	}

	// curl -H 'Content-Type: application/json' -d '{ "idAcount":"1",
	// "idClient":"1", "totalBalance":"1000" }' -X POST
	// http://localhost:8080/saveAccount/2
	@PostMapping(path = "/saveAccount/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> saveAccount(@PathVariable(name = "idClient") Integer idClient,
			@RequestBody Account account) {
		// Save the resource to the database
		Account accountSaved = accountJdbcDao.saveAccount(idClient, account);
		if (accountSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(accountSaved);
	}

	// curl -X GET "http://localhost:8080/findAccountById?idAccount=1"
	@GetMapping("/findAccountById")
	public ResponseEntity<Account> findAccountById(@RequestParam(value = "idAccount", defaultValue = "0") Integer id) {
		System.out.println("AccountController.findById() " + id);
		Account resultAccount = accountJdbcDao.findAccountById(id);
		if (resultAccount == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(resultAccount);
	}

	// curl -X GET "http://localhost:8080/findAllAccounts"
	@GetMapping("/findAllAccounts")
	public ResponseEntity<List<Account>> findAllAccounts() {
		System.out.println("AccountController.findAllAccounts");
		List<Account> accounts = accountJdbcDao.findAllAccounts();
		if (accounts == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(accounts);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteAccount/1
	@DeleteMapping(path = "/deleteAccount/{idAccount}")
	public ResponseEntity<Account> deleteAccount(@PathVariable(name = "idAccount") Integer idAccount) {
		// Retrieve the resource from the database
		Account target = accountJdbcDao.findAccountById(idAccount);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Delete the resource
		accountJdbcDao.deleteAccount(idAccount);
		// Return a 204 (no content) status code
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// curl -H 'Content-Type: application/json' -d '{ "idAcount":"1",
	// "idClient":"1", "totalBalance":"500"}' -X PUT
	// http://localhost:8080/updateAccount
	@PutMapping(path = "/updateAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
		// Retrieve the resource from the database
		Integer idAccount = account.getIdAcount();
		Account target = accountJdbcDao.findAccountById(idAccount);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Update the resource
		Account updatedAccount = accountJdbcDao.updateAccount(account);
		// Return the updated resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(updatedAccount);
	}
}
