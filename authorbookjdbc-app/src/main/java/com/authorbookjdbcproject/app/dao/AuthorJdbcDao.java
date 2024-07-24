package com.authorbookjdbcproject.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.authorbookjdbcproject.app.model.Author;
/**
 * This class connects to the database and enables the save, update, find,
 * delete operations to the database
 */
public class AuthorJdbcDao implements AuthorDao{
	private JdbcDataSource dataSource;
	
	public AuthorJdbcDao(JdbcDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Author saveAuthor(Author author) {
		System.out.println("jdbc save author");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO Author(name) " + "VALUES(?)");
			ps.setString(1, author.getName());
			int i = ps.executeUpdate();

			if (i == 1) {
				//ps.getGeneratedKeys()						
				System.out.println("jdbc saved author info to database");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return author;
	}

	@Override
	public Author findAuthorById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> findAllAuthors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author deleteAuthor(Integer idAuthor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author updateAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

}
