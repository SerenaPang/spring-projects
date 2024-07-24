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
					.prepareStatement("INSERT INTO Author(id, name, books) " + "VALUES(?,?,?)");

			ps.setInt(1, author.getId());
			ps.setString(2, author.getName());
			
		//	put list
		//	ps.setNString(3, author.getBooks());
			int i = ps.executeUpdate();

			if (i == 1) {
				System.out.println("jdbc save cosmetic info to database");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
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
