package com.example.pet_house.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet_house.dao.JdbcCatDao;
import com.example.pet_house.model.Cat;

@Service
public class CatService {

	@Autowired
	private JdbcCatDao catDao;

	public void addCat(Cat cat) {
		catDao.saveCat(cat);
	}

	public List<Cat> findAll() {
		return catDao.findAllCats();
	}
	
	public Cat updateCat(Cat cat) {
		return catDao.updateCat(cat);
	}

	public Cat findCatById(Integer idCat) {
		return catDao.findCatById(idCat);
	}

	public Cat deleteCat(Integer id) {
		return catDao.deleteCat(id);
	}	
}
