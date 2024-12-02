package com.example.theatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.example.theatre.dao.TheaterDao;
import com.example.theatre.model.Theater;

@Service
@ApplicationScope
public class TheaterService {
	@Autowired
	private TheaterDao theaterDao;
	
	public List<Theater> findAll(){
		return theaterDao.findAllTheaters();
	}
	
	public Theater findTheaterByZipcode(String zipcode) {
		return theaterDao.findTheaterByZip(zipcode);
	}
}
