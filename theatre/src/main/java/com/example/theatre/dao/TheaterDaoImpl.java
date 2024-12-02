package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.theatre.model.Theater;

@Repository
public class TheaterDaoImpl implements TheaterDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public Theater findTheaterByZip(String zipcode) {
		System.out.println("jdbc find theater by zipcode " + zipcode);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("SELECT theater_id, zipcode, city FROM THEATER WHERE zipcode =?");
			ps.setString(1, zipcode);
			Theater theater = new Theater();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					theater.setId(rs.getInt("theater_id"));
					theater.setZipcode(rs.getString("zipcode"));
					theater.setCity(rs.getString("city"));
					System.out.println(theater.toString());
				}
				return theater;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Theater> findAllTheaters() {
		System.out.println("jdbc find all theaters");
		List<Theater> theaters = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT theater_id, zipcode, city from Theater");

			while (rs.next()) {
				Theater theater = new Theater();
				theater.setId(rs.getInt("theater_id"));
				theater.setZipcode(rs.getString("zipcode"));
				theater.setCity(rs.getString("city"));
				theaters.add(theater);
				System.out.println(theater.toString());
			}
			System.out.println(theaters);
			return theaters;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return theaters;
	}
}
