package com.learningbatch.app.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learningbatch.app.model.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductBatchRepo implements ProductRepo {
	private final JdbcTemplate jdbcTemplate;

	public ProductBatchRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public void save(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void saveAll(List<Product> products) {
		jdbcTemplate.batchUpdate("INSERT INTO PRODUCT (id, brand) VALUES (?, ?)", products, 100,
				new ParameterizedPreparedStatementSetter<Product>() {
					@Override
					public void setValues(PreparedStatement ps, Product product) throws SQLException {
						ps.setInt(1, product.getId());
						ps.setString(2, product.getBrand());
					}
				});
	}

}
