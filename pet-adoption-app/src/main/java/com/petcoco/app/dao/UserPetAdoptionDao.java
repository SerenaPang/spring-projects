package com.petcoco.app.dao;

import java.util.List;

import com.petcoco.app.model.User;

public interface UserPetAdoptionDao {
	public void saveAdoptionRecord();
	public List<User> findAllAdoptionRecord();
	public void deleteAdoptionRecord();
	public void updateAdoptionRecord();
	
}
