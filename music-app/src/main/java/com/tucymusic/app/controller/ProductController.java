package com.tucymusic.app.controller;

import java.util.List;

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

import com.tucymusic.app.dao.jdbc.ProductDaoImpl;
import com.tucymusic.app.model.Genre;
import com.tucymusic.app.model.Product;
import com.tucymusic.app.model.ProductType;

@RestController
public class ProductController {
	@Autowired
	private ProductDaoImpl jdbcProductDao;
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"6", "productType":"1","name":"I see fire", "genreId":"1", "price":"20"}' -X POST http://localhost:8080/createProduct
	@PostMapping("/createProduct{product}")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		jdbcProductDao.create(product);
		if (product == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	};
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"5", "genre":"Phonk"}' -X POST http://localhost:8080/createGenre
	@PostMapping("/createGenre{genre}")
	public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
		jdbcProductDao.create(genre);
		if (genre == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(genre);
	};
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"6", "description":"Body Lotion"}' -X POST http://localhost:8080/createProductType
	@PostMapping("/createProductType{type}")
	public ResponseEntity<ProductType> createProductType(@RequestBody ProductType type) {
		jdbcProductDao.create(type);
		if (type == null) {
			return null;
		}
		// Return the created resource with a 201 (created) status code
		return ResponseEntity.status(HttpStatus.CREATED).body(type);
	};
	
	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "productType":"1","name":"Flowers", "genreId":"1", "price":"10"}' -X PUT http://localhost:8080/updateProduct
	@PutMapping(path = "/updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Integer productId = product.getId();
		Product target = jdbcProductDao.findProductById(productId);
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		jdbcProductDao.update(product);
		// Return the updated resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(product);
	};

	//TODO:FIX BUG
	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "genre":"Milkyky"}' -X PUT http://localhost:8080/updateGenre
	@PutMapping(path = "/updateGenre", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Genre> updateGenre(@RequestBody Genre genre) {	
		Integer genreId = genre.getId();
		Genre target = jdbcProductDao.findGenreById(genreId);
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		jdbcProductDao.update(genre);
		// Return the updated resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(genre);
	};
	

	// curl -H 'Content-Type: application/json' -d '{ "id":"1", "description":"Milkyky"}' -X PUT http://localhost:8080/updateProductType
	@PutMapping(path = "/updateProductType", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductType> updateProductType(@RequestBody ProductType type) {
		Integer typeId = type.getId();
		ProductType target = jdbcProductDao.findProductTypeById(type.getId());
		if (target == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		jdbcProductDao.update(type);
		// Return the updated resource with a 200 (OK) status code
		return ResponseEntity.status(HttpStatus.OK).body(type);
	};
	
	// curl -X GET "http://localhost:8080/findAllGenres"
	@GetMapping("/findAllGenres")
	public ResponseEntity<List<Genre>> findAllGenres(){
		jdbcProductDao.findAllGenres();
		return null;
	};

	// curl -X GET "http://localhost:8080/findAllProductTypes"
	@GetMapping("/findAllProductTypes")
	public ResponseEntity<List<ProductType>> findAllProductTypes(){
		jdbcProductDao.findAllProductTypes();
		return null;
	};
	
	// curl -X GET "http://localhost:8080/findById?id=1"
	@GetMapping("/findProductById")
	public Product findProductById(@RequestParam(value = "id", defaultValue = "0") int id) {
		jdbcProductDao.findProductById(id);
		return null;
	};
	
	@GetMapping("/findProductsByGenre")
	public ResponseEntity<List<Product>> findProductsByGenre(Genre genre){
		jdbcProductDao.findProductsByGenre(genre);
		return null;
	};
	
	public ResponseEntity<List<Product>> findProductsByProductType(ProductType productType){
		jdbcProductDao.findProductsByProductType(productType);
		return null;
	};
	
	public ResponseEntity<List<Product>> findProductsByGenreAndProductType(Genre genre, ProductType productType){
		jdbcProductDao.findProductsByGenreAndProductType(genre, productType);
		return null;
	};
}
