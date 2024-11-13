package com.example.pet_house.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatRepository {
	@Autowired
	private CatRepository catRepository;
	
	public CatRepository getCatRepository() {
		return catRepository;
	}
}
