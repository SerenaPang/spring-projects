package com.tucymusic.app.dao;

import java.util.List;

import com.tucymusic.app.model.User;

public interface UserDao {

	public void create(User user);
	public void update(User user);
	public User findById(long id);
	public List<User> findAll();
	
}
