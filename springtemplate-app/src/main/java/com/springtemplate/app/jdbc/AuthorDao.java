package com.springtemplate.app.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.springtemplate.app.model.Author;
import com.springtemplate.app.model.Book;

@Repository
public class AuthorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
	static class BookRowMapper implements RowMapper<Book> {
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book();
			book.setId(rs.getInt(1));
			book.setName(rs.getString(2));
			book.setIsbn(rs.getString(3));
			book.setAuthorId(rs.getInt(4));
			return book;
		}
	}

	public Author findAuthorById(Long id) {
		System.out.println("AuthorDao.findAuthorById() - using namedParameterJdbcTemplate");
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("authorId", id);
		return namedParameterJdbcTemplate.queryForObject("select id, name from author where id = :authorId", namedParameters,
				new AuthorRowMapper());
	}
//
//	public Author findById(Long id) {
//		String query = "select id, name from author where id = ?";
//
//		Author author = jdbcTemplate.queryForObject(query, new AuthorRowMapper(), id);
//		return author;
//	}
	
	public List<Author> findAllAuthors() {
		return this.jdbcTemplate.query( "select id, name from author", new AuthorRowMapper());
	}

	public List<Author> getAllAuthors() {
		return jdbcTemplate.query("select id, name from author", new AuthorRowMapper());
	}
	
	public void save(Author author) {
		jdbcTemplate.update("insert into author(id, name) values(?,?)", author.getId(), author.getName());
	}

	public void update(Author author) {
		jdbcTemplate.update("update author set id = ?, name = ? where id = ?", author.getId(), author.getName());
	}

	public void delete(int authorId) {
		jdbcTemplate.update("delete from author where id = ?");
	}

	public Book findBookById(Long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject("select id, name, isbn, id_author from book where id = :id", namedParameters,
				new BookRowMapper());
	}

	public List<Book> findAllBooks() {
		return jdbcTemplate.query("select id, name, isbn, id_author from book", new BookRowMapper());
	}

	public void save(Book book) {
		jdbcTemplate.update("insert into book(id, name, isbn, id_author) values(?,?,?,?)", book.getId(), book.getName(), book.getIsbn(), book.getAuthorId());
	}

	public void update(Book book) {
		jdbcTemplate.update("update book set id = ?, name = ?, isbn = ?, id_author =? where id = ?", book.getId(), book.getName(), book.getIsbn(), book.getAuthorId());
	}

	public void delete(Book book) {
		jdbcTemplate.update("delete from book where id = ?");
	}
}
