package com.tucymusic.app.dao;

import java.util.List;

import com.tucymusic.app.model.Genre;
import com.tucymusic.app.model.Product;
import com.tucymusic.app.model.ProductType;

public interface ProductDao {

	public void create(Product product);
	public void update(Product product);

	public void create(Genre genre);
	public void update(Genre genre);
	public List<Genre> findAllGenres();

	public void create(ProductType type);
	public void update(ProductType type);
	public List<ProductType> findAllProductTypes();
	
	public Product findProductById(long id);
	public List<Product> findProductsByGenre(Genre genre);
	public List<Product> findProductsByProductType(ProductType productType);
	public List<Product> findProductsByGenreAndProductType(Genre genre, ProductType productType);
}
