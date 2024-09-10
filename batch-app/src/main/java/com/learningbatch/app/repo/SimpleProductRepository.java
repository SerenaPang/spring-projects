package com.learningbatch.app.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learningbatch.app.model.Product;

@Repository
public class SimpleProductRepository implements ProductRepository {
	private final JdbcTemplate jdbcTemplate;

	public SimpleProductRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public void saveAll(List<Product> products) {
		for (Product product : products) {			
			jdbcTemplate.update("INSERT INTO PRODUCT (TITLE, CREATED_TS, PRICE) " + "VALUES (?, ?, ?)",
					product.getTitle(), Timestamp.valueOf(product.getCreatedTs()), product.getPrice());
		}
	}
}
