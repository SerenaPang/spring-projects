package com.example.theatre.dao;

import java.util.List;
import com.example.theatre.model.Movie;

public interface MovieDao {
	public List<Movie> findAllMovies(); 
}
