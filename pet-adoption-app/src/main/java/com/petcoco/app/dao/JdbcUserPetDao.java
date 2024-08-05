package com.petcoco.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petcoco.app.model.User;

@Repository
public class JdbcUserPetDao implements UserPetAdoptionDao{
	@Autowired
	private JdbcDataSource datasource;
	
	@Override
	public void saveAdoptionRecord() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAllAdoptionRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdoptionRecord() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAdoptionRecord() {
		// TODO Auto-generated method stub
		
	}

}
