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

import com.tucybank.app.dao.ActivityJdbcDao;
import com.tucybank.app.model.Account;
import com.tucybank.app.model.Activity;

@RestController
public class ActivityController {
	@Autowired
	private ActivityJdbcDao activityJdbcDao;

	public ActivityController() {
	}

	// curl -H 'Content-Type: application/json' -d '{ "idActivity":"1", "idAccount":"3", "date":"2020-03-03", "type":"deposit", "amount":"100"}' -X POST http://localhost:8080/saveActivity/3
	@PostMapping(path = "/saveActivity/{idAccount}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Activity> saveActivity(@PathVariable(name = "idAccount") Integer idAccount,
			@RequestBody Activity activity) {
		// Save the resource to the database
		Activity activitySaved = activityJdbcDao.saveActivity(idAccount, activity);
		if (activitySaved == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(activitySaved);
	}

	// curl -X GET "http://localhost:8080/findActivityById?idActivity=1"
	@GetMapping("/findActivityById")
	public ResponseEntity<Activity> findActivityById(
			@RequestParam(value = "idActivity", defaultValue = "0") Integer id) {
		System.out.println("ActivityController.findActivityById() " + id);
		Activity resultActivity = activityJdbcDao.findActivityById(id);
		if (resultActivity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(resultActivity);
	}

	// curl -X GET "http://localhost:8080/findAllActivities"
	@GetMapping("/findAllActivities")
	public ResponseEntity<List<Activity>> findAllActivities() {
		System.out.println("AccountController.findAllAccounts");
		List<Activity> activities = activityJdbcDao.findAllActivities();
		if (activities == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(activities);
	}
	
	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteActivity/3
	@DeleteMapping(path = "/deleteActivity/{idActivity}")
	public ResponseEntity<Activity> deleteActivity(@PathVariable(name = "idActivity") Integer idActivity) {
		// Retrieve the resource from the database
		Activity target = activityJdbcDao.findActivityById(idActivity);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Delete the resource
		activityJdbcDao.deleteActivity(idActivity);
		// Return a 204 (no content) status code
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// curl -H 'Content-Type: application/json' -d '{"idActivity":"1", "idAccount":"3", "date":"2020-03-03", "type":"deposit", "amount":"500"}' -X PUT http://localhost:8080/updateActivity
	@PutMapping(path = "/updateActivity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Activity> updateActivity(@RequestBody Activity activity) {
		// Retrieve the resource from the database
		Integer idActivity = activity.getIdActivity();
		Activity target = activityJdbcDao.findActivityById(idActivity);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Update the resource
		Activity updatedActivity = activityJdbcDao.updateActivity(activity);
		// Return the updated resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(updatedActivity);
	}
}
