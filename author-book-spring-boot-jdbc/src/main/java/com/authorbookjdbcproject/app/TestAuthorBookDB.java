package com.authorbookjdbcproject.app;

import java.sql.Connection;

import com.authorbookjdbcproject.app.dao.JdbcDataSource;
/**
 * Get connected to the database
 * */
public class TestAuthorBookDB {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Pass the database.properties file");
//			System.out.println("java -classpath ./target/authorbookjdbcproject-app-1.0-SNAPSHOT-jar-with-dependencies.jar \\\n"
//					+ "    com.mycompany.app.TestAuthorBookDB  \\\n"
//					+ "    ./src/main/java/com/authorbookjdbcproject/app/mysqldb.properties");
			return;
		}

		JdbcDataSource dataSource = new JdbcDataSource(args[0]);
		try(Connection conn = dataSource.getConnection()){
			
			System.out.println("Connected " + conn.getCatalog());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
