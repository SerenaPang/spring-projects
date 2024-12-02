package com.example.theatre.dao;

import java.util.List;
import com.example.theatre.model.Theater;

public interface TheaterDao {
	public Theater findTheaterByZip(String zipcode);
	public List<Theater> findAllTheaters();	
}
