package com.petcoco.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.petcoco.app.dao.JdbcUserPetDao;
import com.petcoco.app.model.Record;

@RestController
public class UserPetAdoptionRecordController {
	@Autowired
	private JdbcUserPetDao userPetDao;
	
	// curl -X GET "http://localhost:8080/findAllRecords"
	@GetMapping("/findAllRecords")
	public ResponseEntity<List<Record>> findAllRecords() {
		List<Record> records = userPetDao.findAllAdoptionRecord();
		if (records == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(records);
	}
	
	// curl -X GET "http://localhost:8080/findRecordByUserId?id=1"
	@GetMapping("/findRecordByUserId")
	public ResponseEntity<Record> findRecordByUserId(@RequestParam(value = "id", defaultValue = "0") Integer idUser) {
		System.out.println("UserPetController.findRecordByUserId() " + idUser);
		Record resultRecord = userPetDao.findRecordByUserId(idUser);
		if (resultRecord == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(resultRecord);
	}
	

}