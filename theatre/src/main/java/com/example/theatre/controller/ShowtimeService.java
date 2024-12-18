package com.example.theatre.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.example.theatre.dao.ShowtimeDao;
import com.example.theatre.model.Showtime;

@Service
@ApplicationScope
public class ShowtimeService {
	@Autowired
	private ShowtimeDao showtimeDao;

	public List<Showtime> findAll() {
		return showtimeDao.findAllShowtimes();
	}

//	public Map<String, List<Date>> findShowtimeByMovieName() {
//		return showtimeDao.findShowtimeByMovieName();
//	}

	public List<Showtime> findShowtimeByMovieId(int id) {
		return showtimeDao.findShowtimeByMovieId(id);
	}
}
