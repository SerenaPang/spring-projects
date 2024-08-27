package com.tucymusic.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tucymusic.app.dao.jdbc.OrderDaoImpl;
import com.tucymusic.app.model.Order;
import com.tucymusic.app.model.Product;

@RestController
public class OrderController {
	@Autowired
	private OrderDaoImpl jdbcOrderDao;
	
	// curl -H 'Content-Type: application/json' -d '{ "orderId":"1", "userId":"1","totalPrice":"20", "discount":"0", "saleDate":"2024-08-30  09:15:07","status":"ORDER PLACED"}' -X POST http://localhost:8080/create
		@PostMapping("/create{order}")
		public ResponseEntity<Order> create(@RequestBody Order order) {
			jdbcOrderDao.create(order);
			if (order == null) {
				return null;
			}
			// Return the created resource with a 201 (created) status code
			return ResponseEntity.status(HttpStatus.CREATED).body(order);
		};
		
		// curl -H 'Content-Type: application/json' -d '{ "orderId":"1", "userId":"1","totalPrice":"20", "discount":"0", "saleDate":"2024-08-30  09:15:07","status":"ORDER PROCCSING"}' -X PUT http://localhost:8080/update
		@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Order> update(@RequestBody Order order) {
			Integer orderId = order.getOrderId();
			Order target = jdbcOrderDao.findById(orderId);
			if (target == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			jdbcOrderDao.update(order);
			// Return the updated resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(order);
		};
		
		// curl -X GET "http://localhost:8080/findById?id=1"
		@GetMapping("/findById")
		public ResponseEntity<Order> findById(@RequestParam(value = "id", defaultValue = "0") int id) {
			System.out.println("OrderController.findById() " + id);
			Order resultOrder = jdbcOrderDao.findById(id);
			if (resultOrder == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			// Return the resource with a 200 (OK) status code
			return ResponseEntity.status(HttpStatus.OK).body(resultOrder);
		};
		
}
