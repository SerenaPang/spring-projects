package com.springtemplate.app.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springtemplate.app.model.Author;

@Repository
public class AuthorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcOperations.html#queryForObject(java.lang.String,org.springframework.jdbc.core.RowMapper,java.lang.Object...)
	static class AuthorRowMapper implements RowMapper<Author> {
		@Override
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
			Author author = new Author();
			author.setId(rs.getInt(1));
			author.setName(rs.getString(2));

			return author;
		}
	}

	public Author findByIdWithoutBooks(Long id) {
		String query = "select id, name from author where id = ?";

		Author author = jdbcTemplate.queryForObject(query, new AuthorRowMapper(), id);
		return author;
	}

}
