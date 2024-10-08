package com.authorbookjdbcproject.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.authorbookjdbcproject.app.model.Author;

/**
 * This class connects to the database and enables the save, update, find,
 * delete operations to the database
 */

@Repository
public class AuthorJdbcDao implements AuthorDao {
	@Autowired
	private JdbcDataSource dataSource;

	public AuthorJdbcDao() {
	}

	@Override
	public Author saveAuthor(Author author) {
		System.out.println("jdbc save author");
		Author authorExist = findAuthorById(author.getId());
		if (authorExist.getId() != author.getId() && authorExist.getName() != author.getName()) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Author(name) " + "VALUES(?)");
				ps.setString(1, author.getName());
				int i = ps.executeUpdate();

				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved author info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Author findAuthorById(Integer id) {
		System.out.println("jdbc find author by id " + id);

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT id, name FROM author WHERE id =?");
			ps.setInt(1, id);
			Author author = new Author();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					author.setId(rs.getInt("id"));
					author.setName(rs.getString("name"));
					System.out.println("id: " + author.getId() + " name: " + author.getName());
				}
				return author;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Author> findAllAuthors() {
		System.out.println("jdbc find all authors");
		List<Author> authors = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name from author");

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
		return authors;
	}

	@Override
	public Author deleteAuthor(Integer id) {
		System.out.println("jdbc delete Author");

		Author target = findAuthorById(id);
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM author WHERE id= ?");
				ps.setInt(1, id);
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete Author " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Author updateAuthor(Author author) {
		System.out.println("jdbc update author");
		Author target = findAuthorById(author.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE author SET name=? WHERE id=?");
				ps.setString(1, author.getName());
				ps.setInt(2, author.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update author " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
