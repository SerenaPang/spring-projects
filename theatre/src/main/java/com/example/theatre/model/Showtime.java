package com.example.theatre.model;

import java.sql.Date;
import java.util.List;

public class Showtime {
	private Date showtime;
	private List<Date> showtimes;
	private int movieId;
	private String movieName;

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

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	@Override
	public String toString() {
		return "Showtime [showtime=" + showtime + ", showtimes=" + showtimes + ", movieId=" + movieId + ", movieName="
				+ movieName + "]";
	}
}
