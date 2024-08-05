package com.petcoco.app.controller;

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

import com.petcoco.app.dao.JdbcPetDao;
import com.petcoco.app.model.Pet;


@RestController
public class PetContoller {
	@Autowired
	private JdbcPetDao jdbcPetDao;
	
	public PetContoller() {
		System.out.println("pet controller");
	}
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"6", "name":"Bilibii", "idType": "2", "age": "1", "status":"Available" }' -X POST http://localhost:8080/savePet
		@PostMapping("/savePet{pet}")
		public ResponseEntity<Pet> savePet(@RequestBody Pet pet) {
			Pet petSaved = jdbcPetDao.savePet(pet);
			if (petSaved == null) {
				return null;
			}
			// Return the created resource with a 201 (created) status code
			return ResponseEntity.status(HttpStatus.CREATED).body(petSaved);
		}
		
		// curl -X GET "http://localhost:8080/findPetById?id=1"
		@GetMapping("/findPetById")
		public ResponseEntity<Pet> findPetById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
			System.out.println("petController.findById() " + id);
			Pet resultPet = jdbcPetDao.findPetById(id);
			if (resultPet == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Return the resource with a 200 (OK) status code
			System.out.println("resultPet: " + resultPet.toString());
			return ResponseEntity.status(HttpStatus.OK).body(resultPet);
		}
		
		// curl -X GET "http://localhost:8080/findAllPets"
			@GetMapping("/findAllPets")
			public ResponseEntity<List<Pet>> findAllPets() {
				List<Pet> pets = jdbcPetDao.findAllPets();
				if (pets.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
				// Return the resource with a 200 (OK) status code
				return ResponseEntity.status(HttpStatus.OK).body(pets);
			}
			
			// curl -H 'Content-Type: application/json' -X DELETE http://localhost:8080/deletePet/1
			@DeleteMapping(path = "/deletePet/{idPet}")
			public ResponseEntity<Pet> deletePet(@PathVariable(name = "idPet") Integer idPet) {
				// Retrieve the resource from the database
				Pet target = jdbcPetDao.findPetById(idPet);
				// If the resource is not found, return a 404 (not found) status code
				if (target == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
				// Delete the resource
				jdbcPetDao.deletePet(idPet);
				// Return a 204 (no content) status code
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"Bili", "idType": "1", "age": "10", "status":"Not Available" }' -X PUT http://localhost:8080/updatePet
			@PutMapping(path = "/updatePet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Pet> updateUser(@RequestBody Pet pet) {
				// Retrieve the resource from the database
				Integer idPet = pet.getId();
				Pet target = jdbcPetDao.findPetById(idPet);
				// If the resource is not found, return a 404 (not found) status code
				if (target == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
				// Update the resource
				Pet updatedPet = jdbcPetDao.updatePet(pet);
				// Return the updated resource with a 200 (OK) status code
				return ResponseEntity.status(HttpStatus.OK).body(updatedPet);
			}
			
			// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"Bili", "idType": "1", "age": "10", "status":"Not Available" }' -X PUT http://localhost:8080/updatePet
						@PutMapping(path = "/adoptPet{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
						public ResponseEntity<Pet> adoptPet(@PathVariable(name = "idUser") Integer idUser, @RequestBody Pet pet) {
							// Retrieve the resource from the database
							Integer idPet = pet.getId();
							Pet target = jdbcPetDao.findPetById(idPet);
							// If the resource is not found, return a 404 (not found) status code
							if (target == null) {
								return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
							}
							// Update the resource
							Pet updatedPet = jdbcPetDao.updatePet(pet);
							// Return the updated resource with a 200 (OK) status code
							return ResponseEntity.status(HttpStatus.OK).body(updatedPet);
						}
	
}
