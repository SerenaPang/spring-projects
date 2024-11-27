package com.example.theatre.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

/**
 * A simple data source for getting database connections.
 */
@Component
public class JdbcDataSource {

	public JdbcDataSource() throws ClassNotFoundException, IOException, SQLException {
		init();
	}

	private void init() throws IOException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection con = getConnection()) {
			System.out.println("Connected to " + con.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a connection to the database
	 * 
	 * @return the database connection
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/theater",
				"root", "abcd1234.");
	}
}
