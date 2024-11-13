package com.example.pet_house.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pet_house.model.Cat;

@Service
public class CatService {
private List<Cat> cats = new ArrayList<>();
@Autowired
private CatRepository catRepository;

public CatRepository getCatRepository() {
	return catRepository;
}
	// TODO inject the dao class to do inserts, and findall, etc.

	public void addCat(Cat c) {
		cats.add(c);
	}
	
	public List<Cat> findAll(){
		return cats;
	}
	
	
}
