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
		System.out.println("saveClient");
		Client clientSaved = clientJdbcDao.saveClient(client);
		if (clientSaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(clientSaved);
	}
	
	// curl -X GET "http://localhost:8080/findClientById?idCilent=1"
	@GetMapping("/findClientById")
	public ResponseEntity<Client> findClientById(@RequestParam(value = "idCilent", defaultValue = "0")Integer id){
		System.out.println("findClientById: " + id);
		Client client = clientJdbcDao.findClientById(id);
		if (client == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	// curl -X GET "http://localhost:8080/findAllClients"
	@GetMapping("/findAllClients")
	public ResponseEntity<List<Client>> findAllClients(){
		System.out.println("findAllClients");
		List<Client> clients = clientJdbcDao.findAllClients();
		if (clients== null || clients.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(clients);
	}
	
	// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deleteClient/1
	@DeleteMapping(path = "/deleteClient/{idCilent}")
	public ResponseEntity<Client> deleteClient(@PathVariable(name = "idCilent") Integer idCilent) {
		Client client = clientJdbcDao.findClientById(idCilent);
		if (client == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		clientJdbcDao.deleteClient(idCilent);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// curl -H 'Content-Type: application/json' -d '{"idCilent":"2", "name":"Darake", "lastName":"Qu", "idAccount": "1"}' -X PUT http://localhost:8080/updateClient
	@PutMapping(path = "/updateClient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> updateClient(@RequestBody Client client){
		Integer idClient = client.getIdCilent();
		Client target = clientJdbcDao.findClientById(idClient);
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Client updatedClient = clientJdbcDao.updateClient(client);
		return ResponseEntity.status(HttpStatus.OK).body(updatedClient);
	}	
}
