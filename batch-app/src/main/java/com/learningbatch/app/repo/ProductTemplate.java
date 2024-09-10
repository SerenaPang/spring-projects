package com.learningbatch.app.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.learningbatch.app.model.Product;

public class ProductTemplate implements ProductRepo{
	private final JdbcTemplate jdbcTemplate;
	
	public ProductTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	@Transactional
	public void save(Product product) {
		 jdbcTemplate.update("INSERT INTO PRODUCT (id, brand) VALUES (?, ?)",
				 product.getId(), product.getBrand());
	}

	@Override
	public void saveAll(List<Product> products) {
		  for (Product product : products) {
			  jdbcTemplate.update("INSERT INTO PRODUCT (id, brand) VALUES (?, ?)",
						 product.getId(), product.getBrand());
	        }
	}

}
