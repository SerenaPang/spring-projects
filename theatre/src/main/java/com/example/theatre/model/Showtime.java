package com.example.theatre.model;

import java.sql.Date;
import java.util.List;

public class Showtime {
	private Date showtime;
	private List<Date> showtimes;
	private int movieId;
	private int theaterId;
	

	public Date getShowtime() {
		return showtime;
	}

	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}

	public List<Date> getShowtimes() {
		return showtimes;
	}

	public void setShowtimes(List<Date> showtimes) {
		this.showtimes = showtimes;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	@Override
	public String toString() {
		return "Showtime [showtime=" + showtime + ", showtimes=" + showtimes + ", movieId=" + movieId + ", theaterId="
				+ theaterId + "]";
	}
}
