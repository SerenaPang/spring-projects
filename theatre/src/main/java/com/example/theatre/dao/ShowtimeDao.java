package com.example.theatre.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.example.theatre.model.Showtime;

public interface ShowtimeDao {
	public List<Showtime> findShowtimeByMovieId(int id);
	//public List<Showtime> findShowtimeByMovieName();
	//public Map<String, List<Date>> findShowtimeByMovieName();
	public List<Showtime> findAllShowtimes();
	public Showtime findShowtimeById(int showtimeId);
}
