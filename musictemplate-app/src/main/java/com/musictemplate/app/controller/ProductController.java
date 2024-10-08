package com.musictemplate.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musictemplate.app.jdbc.ProductDao;
import com.musictemplate.app.model.Genre;
import com.musictemplate.app.model.Order;
import com.musictemplate.app.model.OrderItem;
import com.musictemplate.app.model.Product;
import com.musictemplate.app.model.ProductType;
import com.musictemplate.app.model.User;

@RestController
public class ProductController {
	@Autowired
	private ProductDao productDao;

	// curl -X GET "http://localhost:8080/findProductById?id=2"
	@GetMapping("/findProductById")
	public Product findProductById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
		System.out.println("findById()");
		Product author = productDao.findProductById(id);
		return author;
	}

	// curl -X GET "http://localhost:8080/findAllProducts"
	@GetMapping("/findAllProducts")
	public List<Product> findAllProducts() {
		List<Product> prodcuts = productDao.findAllProducts();
		if (prodcuts.isEmpty()) {
			return null;
		}
		return prodcuts;
	}

	// curl -H 'Content-Type: application/json' -d '{ "product_id":"25",
	// "product_type_id":"1", "name":"Pen", "genre_id":"1", "price","12.5"}' -X
	// POST http://localhost:8080/saveProduct
	@PostMapping("/saveProduct{product}")
	public void saveProduct(@RequestBody Product product) {
		productDao.save(product);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteProduct/1
	@DeleteMapping(path = "/deleteProduct/{idProduct}")
	public void deleteProduct(@PathVariable(name = "idProduct") Integer idProduct) {
		productDao.delete(idProduct);

	}

	// curl -H 'Content-Type: application/json' -d '{"product_id":"25",
	// "product_type_id":"1", "name":"Pencil", "genre_id":"1", "price","11.5"}' -X
	// PUT http://localhost:8080/updateProduct
	@PutMapping(path = "/updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateProduct(@RequestBody Product product) {
		// Retrieve the resource from the database
		long idProduct = product.getId();
		Product target = productDao.findProductById(idProduct);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			System.out.println("no product exist");
		}
		// Update the resource
		productDao.update(product);
	}

	// curl -X GET "http://localhost:8080/findProductTypeById?id=2"
	@GetMapping("/findProductTypeById")
	public ProductType findProductTypeById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
		System.out.println("findById()");
		ProductType productType = productDao.findProductTypeById(id);
		return productType;
	}

	// curl -X GET "http://localhost:8080/findAllProductTypes"
	@GetMapping("/findAllProductTypes")
	public List<ProductType> findAllProductTypes() {
		List<ProductType> productTypes = productDao.findAllProductTypes();
		if (productTypes.isEmpty()) {
			return null;
		}
		return productTypes;
	}

	// curl -H 'Content-Type: application/json' -d '{ "product_type_id":"5",
	// "description":"Album"}' -X POST http://localhost:8080/saveProductType
	@PostMapping("/saveProductType{productType}")
	public void saveProductType(@RequestBody ProductType productType) {
		productDao.saveProductType(productType);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteProductType/1
	@DeleteMapping(path = "/deleteProductType/{idProductType}")
	public void deleteProductType(@PathVariable(name = "idProduct") Integer idProductType) {
		productDao.delete(idProductType);

	}

	// curl -H 'Content-Type: application/json' -d '{ "product_type_id":"5",
	// "description":"Song"}' -X
	// PUT http://localhost:8080/updateProductType
	@PutMapping(path = "/updateProductType", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateProductType(@RequestBody ProductType productType) {
		// Retrieve the resource from the database
		long idProductType = productType.getId();
		ProductType target = productDao.findProductTypeById(idProductType);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			System.out.println("no product type exist");
		}
		// Update the resource
		productDao.updateProductType(productType);
	}

	// curl -X GET "http://localhost:8080/findUserById?id=2"
	@GetMapping("/findUserById")
	public User findUserById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
		System.out.println("findUserById()");
		User user = productDao.findUserById(id);
		return user;
	}

	// curl -X GET "http://localhost:8080/findAllUsers"
	@GetMapping("/findAllUsers")
	public List<User> findAllUsers() {
		List<User> users = productDao.findAllUsers();
		if (users.isEmpty()) {
			return null;
		}
		return users;
	}

	// curl -H 'Content-Type: application/json' -d '{ "user_id":"25",
	// "name_user":"Serena"}' -X POST http://localhost:8080/saveUser
	@PostMapping("/saveUser{user}")
	public void saveUser(@RequestBody User user) {
		productDao.saveUser(user);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteUser/1
	@DeleteMapping(path = "/deleteUser/{idUser}")
	public void deleteUser(@PathVariable(name = "idUser") Integer idUser) {
		productDao.deleteUser(idUser);

	}

	// curl -H 'Content-Type: application/json' -d '{"user_id":"25",
	// "name_user":"Serina"}' -X
	// PUT http://localhost:8080/updateUser
	@PutMapping(path = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@RequestBody User user) {
		// Retrieve the resource from the database
		long idUser = user.getId();
		User target = productDao.findUserById(idUser);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			System.out.println("no user exist");
		}
		// Update the resource
		productDao.updateUser(user);
	}

	// curl -X GET "http://localhost:8080/findOrderById?id=2"
	@GetMapping("/findOrderById")
	public Order findOrderById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
		System.out.println("findOrderById()");
		Order order = productDao.findOrdersById(id);
		return order;
	}

	// curl -X GET "http://localhost:8080/findAllOrders"
	@GetMapping("/findAllOrders")
	public List<Order> findAllOrders() {
		List<Order> orders = productDao.findAllOrders();
		if (orders.isEmpty()) {
			return null;
		}
		return orders;
	}

	// curl -H 'Content-Type: application/json' -d '{ "order_id":"2",
	// "user_id":"1","total_price":"20","discount_percentage":"0","sale_date":"2024-08-27
	// 09:15:07", "status":"ORDER PLACED"}' -X POST http://localhost:8080/saveOrder
	@PostMapping("/saveOrder{order}")
	public void saveOrder(@RequestBody Order order) {
		productDao.saveOrders(order);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteOrder/1
	@DeleteMapping(path = "/deleteOrder/{idOrder}")
	public void deleteOrder(@PathVariable(name = "idOrder") Integer idOrder) {
		productDao.deleteOrders(idOrder);

	}

	// curl -H 'Content-Type: application/json' -d '{"order_id":"2",
	// "user_id":"1","total_price":"20","discount_percentage":"0","sale_date":"2024-08-27
	// 09:15:07", "status":"Order Processing"}' -X PUT
	// http://localhost:8080/updateOrder
	@PutMapping(path = "/updateOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateOrder(@RequestBody Order order) {
		// Retrieve the resource from the database
		long idOrder = order.getOrderId();
		Order target = productDao.findOrdersById(idOrder);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			System.out.println("no order exist");
		}
		// Update the resource
		productDao.updateOrders(order);
	}

	// curl -X GET "http://localhost:8080/findGenreById?id=2"
	@GetMapping("/findGenreById")
	public Genre findGenreById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
		System.out.println("findUserById()");
		Genre genre = productDao.findGenreById(id);
		return genre;
	}

	// curl -X GET "http://localhost:8080/findAllGenres"
	@GetMapping("/findAllGenres")
	public List<Genre> findAllGenres() {
		List<Genre> genres = productDao.findAllGenres();
		if (genres.isEmpty()) {
			return null;
		}
		return genres;
	}

	// curl -H 'Content-Type: application/json' -d '{ "genre_id":"2",
	// "genre":"pop"}' -X POST http://localhost:8080/saveGenre
	@PostMapping("/saveGenre{genre}")
	public void saveGenre(@RequestBody Genre genre) {
		productDao.saveGenre(genre);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteGenre/1
	@DeleteMapping(path = "/deleteGenre/{idGenre}")
	public void deleteGenre(@PathVariable(name = "idGenre") Integer idGenre) {
		productDao.delete(idGenre);

	}

	// curl -H 'Content-Type: application/json' -d '{"genre_id":"2", "genre":"pop"}'
	// -X PUT http://localhost:8080/updateGenre
	@PutMapping(path = "/updateGenre", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateGenre(@RequestBody Genre genre) {
		// Retrieve the resource from the database
		long idGenre = genre.getId();
		Genre target = productDao.findGenreById(idGenre);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			System.out.println("no genre exist");
		}
		// Update the resource
		productDao.updateGenre(genre);
	}

	// curl -X GET "http://localhost:8080/findOrderItemById?id=2"
	@GetMapping("/findOrderItemById")
	public OrderItem findOrderItemById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
		System.out.println("findById()");
		OrderItem orderItem = productDao.findOrderItemById(id);
		return orderItem;
	}

	// curl -X GET "http://localhost:8080/findAllOrderItem"
	@GetMapping("/findAllOrderItem")
	public List<OrderItem> findAllOrderItem() {
		List<OrderItem> orderItems = productDao.findAllOrderItem();
		if (orderItems.isEmpty()) {
			return null;
		}
		return orderItems;
	}

	// curl -H 'Content-Type: application/json' -d
	// '{"order_item_id":"2","order_id":"1", "product_id":"1", "quantity":"10",
	// "price","12.5"}' -X POST http://localhost:8080/saveOrderItem
	@PostMapping("/saveOrderItem{orderItem}")
	public void saveOrderItem(@RequestBody OrderItem orderItem) {
		productDao.saveOrderItem(orderItem);
	}

	// curl -H 'Content-Type: application/json' -X DELETE
	// http://localhost:8080/deleteOrderItem/1
	@DeleteMapping(path = "/deleteProduct/{idOrderItem}")
	public void deleteOrderItem(@PathVariable(name = "idOrderItem") Integer idOrderItem) {
		productDao.deleteOrderItem(idOrderItem);

	}

	// curl -H 'Content-Type: application/json' -d
	// '{"order_item_id":"2","order_id":"1", "product_id":"1", "quantity":"10",
	// "price","12.5"}' -X PUT http://localhost:8080/updateOrderItem
	@PutMapping(path = "/updateOrderItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateOrderItem(@RequestBody OrderItem orderItem) {
		// Retrieve the resource from the database
		long idOrderItem = orderItem.getOrderItemId();
		OrderItem target = productDao.findOrderItemById(idOrderItem);
		// If the resource is not found, return a 404 (not found) status code
		if (target == null) {
			System.out.println("no order item exist");
		}
		// Update the resource
		productDao.updateOrderItem(orderItem);
	}
}
