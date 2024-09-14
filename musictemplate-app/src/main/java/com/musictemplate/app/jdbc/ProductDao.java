package com.musictemplate.app.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.musictemplate.app.model.Product;
import com.musictemplate.app.model.User;


@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcOperations.html#queryForObject(java.lang.String,org.springframework.jdbc.core.RowMapper,java.lang.Object...)
		static class ProductRowMapper implements RowMapper<Product> {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProductType(rs.getInt(2));
				product.setName(rs.getString(3));
				product.setGenreId(rs.getInt(4));
				product.setPrice(rs.getBigDecimal(5));
				return product;
			}
		}
		static class UserRowMapper implements RowMapper<User> {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				return user;
			}
		}

		public Product findProductById(Long id) {
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
			return namedParameterJdbcTemplate.queryForObject("select product_id, product_type_id, name, genre_id, price from Product where product_id = :product_id", namedParameters,
					new ProductRowMapper());
		}
}
