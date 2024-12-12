package com.example.theatre.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Showtime {
	private Date showtime;
	private List<Date> showtimes;
	private int movieId;
	private int theaterId;
	private String name;
	private Map<String, Date> mapOfShowtimes;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Date> getMapOfShowtimes() {
		return mapOfShowtimes;
	}

	public void setMapOfShowtimes(Map<String, Date> mapOfShowtimes) {
		this.mapOfShowtimes = mapOfShowtimes;
	}

	@Override
	public String toString() {
		return "Showtime [showtime=" + showtime + ", showtimes=" + showtimes + ", movieId=" + movieId + ", theaterId="
				+ theaterId + "]";
	}
}
