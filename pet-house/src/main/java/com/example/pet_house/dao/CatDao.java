package com.example.pet_house.dao;

import java.util.List;
import com.example.pet_house.model.Cat;

public interface CatDao {
	public Cat saveCat(Cat cat);
	public Cat findCatById(Integer id);
	public List<Cat> findAllCats();
	public Cat deleteCat(Integer idCat);
	public Cat updateCat(Cat cat);
}
