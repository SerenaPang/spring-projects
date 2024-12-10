package com.example.theatre.dao;

import java.util.List;
import com.example.theatre.model.Showtime;

public interface ShowtimeDao {
	public List<Showtime> findShowtimeByMovieId(int id);
	public List<Showtime> findShowtimeByMovieName();
	public List<Showtime> findAllShowtimes();
}
