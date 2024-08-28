package com.tucymusic.app.controller;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tucymusic.app.dao.jdbc.OrderDaoImpl;
import com.tucymusic.app.model.Order;
import com.tucymusic.app.model.OrderItem;
import com.tucymusic.app.model.User;

@RestController
public class OrderController {
	@Autowired
	private OrderDaoImpl jdbcOrderDao;

	// curl -H 'Content-Type: application/json' -d '{ "orderId":"1",
	// "userId":"1","totalPrice":"20", "discount":"0", "saleDate":"2024-08-30
	// 09:15:07","status":"ORDER PLACED"}' -X POST http://localhost:8080/create
	@PostMapping("/create{order}")
	public ResponseEntity<Order> create(@RequestBody Order order) {
		jdbcOrderDao.create(order);
		if (order == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	};

	// curl -H 'Content-Type: application/json' -d '{ "orderId":"1",
	// "userId":"1","totalPrice":"20", "discount":"0", "saleDate":"2024-08-30
	// 09:15:07","status":"ORDER PROCCSING"}' -X PUT http://localhost:8080/update
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

	// curl -X GET "http://localhost:8080/findAll"
	@GetMapping("/findAll")
	public ResponseEntity<List<Order>> findAll() {
		List<Order> orders = jdbcOrderDao.findAll();
		if (orders == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	};

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/remove/1
	@DeleteMapping(path = "/remove/{idOrder}")
	public ResponseEntity<Order> remove(@PathVariable(name = "idOrder") Integer idOrder) {
		// Retrieve the resource from the database
		Order target = jdbcOrderDao.findById(idOrder);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Delete the resource
		jdbcOrderDao.remove(idOrder);
		// Return a 204 (no content) status code
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// curl -X GET "http://localhost:8080/findByUser?id=1&name=Coco"
	@RequestMapping(value = "/findByUser", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> findByUser(User user) {
		List<Order> orders = jdbcOrderDao.findByUser(user);
		System.out.println(user);
		if (orders == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	};

	// curl -X GET "http://localhost:8080/findByStatus?status=ORDER PLACED"
	@RequestMapping(value = "/findByStatus", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> findByStatus(String status) {
		List<Order> orders = jdbcOrderDao.findByStatus(status);
		System.out.println(status);
		if (orders == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	};

	// curl -X GET "http://localhost:8080/findByDate?saleDate=2024-08-27 09:15:07"
	@RequestMapping(value = "/findByDate", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> findByDate(Date date) {
		List<Order> orders = jdbcOrderDao.findByDate(date);
		System.out.println(date);
		if (orders == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Return the resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	};

	// curl -H 'Content-Type: application/json' -d '{ "orderItemId":"4",
	// "orderId":"1", "productId":"1", "quantity":"2","price":"10" }' -X POST
	// http://localhost:8080/addOrderItem/3
	@PostMapping(path = "/addOrderItem/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderItem> addOrderItem(@PathVariable(name = "orderId") Integer orderId,
			@RequestBody OrderItem orderItem) {
		// Save the resource to the database
		jdbcOrderDao.addOrderItem(orderId, orderItem);
		if (orderItem == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(orderItem);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/removeOrderItem/1
	@DeleteMapping(path = "/removeOrderItem/{orderId}")
	public ResponseEntity<OrderItem> removeOrderItem(@PathVariable(name = "orderId") Integer orderId,
			@RequestBody OrderItem orderItem) {
		// If the resource is not found, return a 404 (not found) status code
		if (orderItem == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// Delete the resource
		jdbcOrderDao.removeOrderItem(orderId, orderItem);
		// Return a 204 (no content) status code
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
