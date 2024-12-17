package com.example.theatre.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Showtime {
	private int showtime_id;
	private Movie movie;
	private Theater theater;
	private Date showtime;

	public int getShowtime_id() {
		return showtime_id;
	}

	public void setShowtime_id(int showtime_id) {
		this.showtime_id = showtime_id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public Date getShowtime() {
		return showtime;
	}

	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}
}
