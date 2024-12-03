package com.example.theatre.dao;

import java.util.List;
import com.example.theatre.model.Drink;

public interface DrinkDao {
	public List<Drink> findAllDrinks();
}
