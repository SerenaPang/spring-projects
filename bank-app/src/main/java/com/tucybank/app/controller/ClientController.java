package com.tucybank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tucybank.app.dao.ClientJdbcDao;
import com.tucybank.app.model.Client;

@RestController
public class ClientController {
	@Autowired
	private ClientJdbcDao clientJdbcDao;
	
	public ClientController() {
		
	}
	
	//curl -H 'Content-Type: application/json' -d '{ "idCilent":"1", "name":"Dirak", "lastName" : "Xu", "idAccount": "1"}' -X POST http://localhost:8080/saveClient
	@PostMapping("saveClient{client}")
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		Client clientSaved = clientJdbcDao.saveClient(client);
		if (clientSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(clientSaved);
	}
}
