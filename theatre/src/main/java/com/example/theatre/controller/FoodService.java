package com.example.theatre.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import com.example.theatre.dao.FoodDao;
import com.example.theatre.model.Food;

@Service
@ApplicationScope
public class FoodService {
	@Autowired
	private FoodDao foodDao;
	
	public List<Food> findAll(){
		return foodDao.FindAllFood();
	}
}
