package com.tucymusic.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tucymusic.app.dao.jdbc.ProductDaoImpl;
import com.tucymusic.app.model.Genre;
import com.tucymusic.app.model.Product;
import com.tucymusic.app.model.ProductType;

@RestController
public class ProductController {
	@Autowired
	private ProductDaoImpl jdbcProductDao;
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"Ava"}' -X POST http://localhost:8080/createProduct
	@PostMapping("/createProduct{product}")
	public void createProduct(Product product) {
		
	};
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"Ava"}' -X POST http://localhost:8080/createGenre
	@PostMapping("/createGenre{genre}")
	public void createGenre(Genre genre) {
		
	};
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "name":"Ava"}' -X POST http://localhost:8080/create
	@PostMapping("/createProductType{type}")
	public void createProductType(ProductType type) {
		
	};
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"Milkyky"}' -X PUT http://localhost:8080/updateProduct
	@PutMapping(path = "/updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateProduct(Product product) {
		
	};

	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"Milkyky"}' -X PUT http://localhost:8080/updateGenre
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateGenre(Genre genre) {
		
	};
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "name":"Milkyky"}' -X PUT http://localhost:8080/updateProductType
	@PutMapping(path = "/updateProductType", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateProductType(ProductType type) {
		
	};
	
	// curl -X GET "http://localhost:8080/findAllGenres"
	@GetMapping("/findAllGenres")
	public List<Genre> findAllGenres(){
		return null;
	};

	// curl -X GET "http://localhost:8080/findAllProductTypes"
	@GetMapping("/findAllProductTypes")
	public List<ProductType> findAllProductTypes(){
		return null;
	};
	
	// curl -X GET "http://localhost:8080/findById?id=1"
		@GetMapping("/findById")
	public Product findProductById(int id) {
		return null;
	};
	
	@GetMapping("/findProductsByGenre")
	public List<Product> findProductsByGenre(Genre genre){
		return null;
	};
	
	public List<Product> findProductsByProductType(ProductType productType){
		return null;
	};
	
	public List<Product> findProductsByGenreAndProductType(Genre genre, ProductType productType){
		return null;
	};
}
