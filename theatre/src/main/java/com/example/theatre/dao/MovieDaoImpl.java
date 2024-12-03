package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.theatre.model.Movie;

@Repository
public class MovieDaoImpl implements MovieDao{
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public List<Movie> findAllMovies() {
		System.out.println("jdbc find all movies");
		List<Movie> movies = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT movie_id, movie_name, price from Movie");

			while (rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getInt("movie_id"));
				movie.setName(rs.getString("movie_name"));
				movie.setPrice(rs.getDouble("price"));
				movies.add(movie);
				System.out.println(movie.toString());
			}
			System.out.println(movies);
			return movies;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return movies;
	}
}
