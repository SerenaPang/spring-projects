package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.theatre.model.Showtime;

@Repository
public class ShowtimeDaoImpl implements ShowtimeDao{
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public List<Showtime> findShowtimeByMovieName(String name) {
		List<Showtime> showtimes = new ArrayList<>();
		
		return showtimes;
	}

	@Override
	public List<Showtime> findAllShowtimes() {
		System.out.println("jdbc find all showtimes");
		List<Showtime> showtimes = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("select movie_id, theater_id, showtime from SHOWTIME");
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Showtime showtime = new Showtime();
					showtime.setMovieId(rs.getInt("movie_id"));
					showtime.setTheaterId(rs.getInt("theater_id"));
					showtime.setShowtime(rs.getDate("showtime"));
					showtimes.add(showtime);
					
				}
				
				System.out.println(showtimes.toString());
				
				return showtimes;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Showtime> findShowtimeByMovieId(int id) {
		System.out.println("jdbc find showtime by movie id " + id);
		List<Showtime> showtimes = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("select s.showtime_id, m.movie_name from movie m, showtime s where m.movie_id = s.movie_id and m.movie_id =?");
			ps.setInt(1, id);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Showtime showtime = new Showtime();
					showtime.setMovieId(rs.getInt("movie_id"));
					showtime.setShowtime(rs.getDate("showtime"));
					showtimes.add(showtime);
					return showtimes;
				}
				return null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
