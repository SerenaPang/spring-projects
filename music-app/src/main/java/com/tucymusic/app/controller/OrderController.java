package com.tucymusic.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tucymusic.app.dao.jdbc.OrderDaoImpl;
import com.tucymusic.app.model.Order;

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
		
		
		
}
