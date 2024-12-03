package com.example.theatre.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import com.example.theatre.dao.MovieDao;
import com.example.theatre.model.Movie;

@Service
@ApplicationScope
public class MovieService {
	@Autowired
	private MovieDao movieDao;

	public List<Movie> findAll() {
		return movieDao.findAllMovies();
	}
}
