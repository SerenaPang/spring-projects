package com.tucymusic.app.dao.jdbc;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

/**
 * A simple data source for getting database connections.
 */
@Component
public class JdbcDataSource {
	private String propertiesFileName = "mysqldb.properties";
	private static String url;
	private static String username;
	private static String password;

	public JdbcDataSource() throws ClassNotFoundException, IOException, SQLException {
		init(propertiesFileName);
	}

	/**
	 * Initialize the data source
	 * 
	 * @param propertiesFile the name of the property file that contains the
	 *                       database driver, URL, username, and password.
	 *
	 *                       Note: propertiesFile has to be in the same directory
	 *                       that {@code JdbcDataSource}
	 */
	private void init(String propertiesFile) throws IOException, ClassNotFoundException {
		Properties props = new Properties();
		InputStream stream = JdbcDataSource.class.getResourceAsStream(propertiesFile);
		props.load(stream);

		String driver = props.getProperty("jdbc.driver");
		url = props.getProperty("jdbc.url");
		username = props.getProperty("jdbc.username");
		password = props.getProperty("jdbc.password");

		Class.forName(driver);

		try(Connection con = getConnection()) {
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
		return DriverManager.getConnection(url, username, password);
	}
}
