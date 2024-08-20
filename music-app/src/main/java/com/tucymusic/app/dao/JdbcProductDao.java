package com.tucymusic.app.dao;

import java.util.List;

import com.tucymusic.app.model.Genre;
import com.tucymusic.app.model.Product;
import com.tucymusic.app.model.ProductType;

public class JdbcProductDao implements ProductDao {

	@Override
	public void create(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Genre genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Genre genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Genre> findAllGenres() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(ProductType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProductType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductType> findAllProductTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByGenre(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByProductType(ProductType productType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByGenreAndProductType(Genre genre, ProductType productType) {
		// TODO Auto-generated method stub
		return null;
	}

}
