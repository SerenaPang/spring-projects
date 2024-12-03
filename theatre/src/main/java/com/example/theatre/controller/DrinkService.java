package com.example.theatre.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import com.example.theatre.dao.DrinkDao;
import com.example.theatre.model.Drink;

@Service
@ApplicationScope
public class DrinkService {
	@Autowired
	private DrinkDao drinkDao;
	
	public List<Drink> findAll(){
		return drinkDao.findAllDrinks();
	}
}
