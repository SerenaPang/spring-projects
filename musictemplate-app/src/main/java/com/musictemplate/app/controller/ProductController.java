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
import com.musictemplate.app.model.Product;


@RestController
public class ProductController {
	@Autowired
	private ProductDao productDao;
	
	// curl -X GET "http://localhost:8080/findProductById?id=2"
		@GetMapping("/findProductById")
		public Product findProductById(@RequestParam(value = "id", defaultValue = "-1") Long id) {
			System.out.println("AuthorController.findById()");
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

		// curl -H 'Content-Type: application/json' -d '{ "id":"25", "name":"AAAB"}' -X
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

		// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"MMMMM"}' -X
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
		
		
		
}
