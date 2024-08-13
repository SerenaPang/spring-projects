package com.tucyticket.app.dao;

import java.util.List;

import com.tucyticket.app.model.User;

public interface UserDao {
	public User saveUser(User user);
	public User findUserById(Integer id);
	public List<User> findAllUsers();
	public User deleteUser(Integer idUser);
	public User updateUser(User user);
}
