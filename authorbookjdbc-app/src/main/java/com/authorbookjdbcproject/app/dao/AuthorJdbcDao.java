package com.authorbookjdbcproject.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		System.out.println("jdbc find author by id");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("SELECT id, name FROM author WHERE id =?");

			ps.setInt(1, id);
			Author author = new Author();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					author.setId(rs.getInt("id"));
					author.setName(rs.getString("name"));
					
				}
			}
			System.out.println(
					"id: " + author.getId() + " name: " + author.getName() );
			return author;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Author> findAllAuthors() {
		System.out.println("jdbc find all authors");
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name from author");
			List<Author> authors = new ArrayList<>();
			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));		
				authors.add(author);
			}
			System.out.println(authors);
			return authors;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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
