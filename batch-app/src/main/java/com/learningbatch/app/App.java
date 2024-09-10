package com.learningbatch.app;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		{
			System.out.println("Getting the connection!");

			// Getting the connection
			String mysqlUrl = "jdbc:mysql://localhost/sampleDB";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
			System.out.println("Connection established......");
			// CREATE TABLE Dispatches( Product_Name VARCHAR(255), Name_Of_Customer
			// VARCHAR(255), Month_Of_Dispatch VARCHAR(255), Price INT, Location
			// VARCHAR(255));
			// Setting auto-commit false
			con.setAutoCommit(false);
			// Creating a PreparedStatement object
			CallableStatement cstmt = con.prepareCall("{call myProcedure(?, ?, ?, ?, ?)}");
			cstmt.setString(1, "Keyboard");
			cstmt.setString(2, "Amith");
			cstmt.setString(3, "January");
			cstmt.setInt(4, 1000);
			cstmt.setString(5, "Hyderabad");
			cstmt.addBatch();
			cstmt.setString(1, "Earphones");
			cstmt.setString(2, "Sumith");
			cstmt.setString(3, "March");
			cstmt.setInt(4, 500);
			cstmt.setString(5, "Vishakhapatnam");
			cstmt.addBatch();
			cstmt.setString(1, "Mouse");
			cstmt.setString(2, "Sudha");
			cstmt.setString(3, "September");
			cstmt.setInt(4, 200);
			cstmt.setString(5, "Vijayawada");
			cstmt.addBatch();
			// Executing the batch
			cstmt.executeBatch();
			// Saving the changes
			con.commit();
			System.out.println("Records inserted......");
		}
	}
}
