package com.example.pet_house.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pet_house.model.Cat;

@Repository
public class JdbcCatDao implements CatDao{
	@Autowired
	private  JdbcDataSource dataSource;

	@Override
	public Cat saveCat(Cat cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cat findCatById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cat> findAllCats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cat deleteCat(Integer idCat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cat updateCat(Cat cat) {
		// TODO Auto-generated method stub
		return null;
	}
}
