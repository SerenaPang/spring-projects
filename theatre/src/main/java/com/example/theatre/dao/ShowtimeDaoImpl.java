package com.example.theatre.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.theatre.model.Showtime;

@Repository
public class ShowtimeDaoImpl implements ShowtimeDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public Map<String, List<Date>> findShowtimeByMovieName() {
		System.out.println("jdbc find all showtimes with movie names");
		Map<String, List<Date>> showtimes = new HashMap<String, List<Date>>();

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"select m.movie_name, s.showtime "
					+ "from movie m, showtime s "
					+ "where s.movie_id = m.movie_id and s.showtime >= CURDATE() "
					+ "order by m.movie_name, s.showtime");

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Showtime showtime = new Showtime();
					List<Date> listShowtimes;
					showtime.setName(rs.getString("movie_name"));
					showtime.setShowtime(rs.getTimestamp("showtime"));
					String name = showtime.getName();
					if (showtimes.containsKey(name)) {
						listShowtimes = showtimes.get(name);
						listShowtimes.add(showtime.getShowtime());
					} else {
						listShowtimes = new ArrayList<Date>();
						listShowtimes.add(showtime.getShowtime());
						showtimes.put(name, listShowtimes);
					}
				}
				System.out.println(showtimes.toString());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return showtimes;
	}
//	@Override
//	public List<Showtime> findShowtimeByMovieName() {
//		System.out.println("jdbc find all showtimes with movie names");
//		List<Showtime> showtimes = new ArrayList<>();
//		try (Connection connection = dataSource.getConnection()) {
//			PreparedStatement ps = connection
//					.prepareStatement("select m.movie_name, s.showtime from movie m, showtime s");
//			
//			try (ResultSet rs = ps.executeQuery()) {
//				while (rs.next()) {
//					Showtime showtime = new Showtime();
//					showtime.setName(rs.getString("movie_name"));
//					showtime.setShowtime(rs.getDate("showtime"));
//					showtimes.add(showtime);
//				}			
//				System.out.println(showtimes.toString());
//				
//				return showtimes;
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public List<Showtime> findAllShowtimes() {
		System.out.println("jdbc find all showtimes");
		List<Showtime> showtimes = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("select movie_id, theater_id, showtime from SHOWTIME");

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
			PreparedStatement ps = connection.prepareStatement(
					"select s.showtime_id, m.movie_name from movie m, showtime s where m.movie_id = s.movie_id and m.movie_id =?");
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
