package com.example.theatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.theatre.dao.JdbcUserDao;
import com.example.theatre.model.User;
/**
 * This class calls the method from the repository to save/find/update/delete data from the database
 * */
@Service
public class UserService {

	@Autowired
	private JdbcUserDao userDao;

	public void addUser(User user) {
		userDao.saveUser(user);
	}

	public List<User> findAll() {
		return userDao.findAllUsers();
	}
	
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	public User findUserById(Integer idUser) {
		return userDao.findUserById(idUser);
	}

	public User deleteUser(Integer id) {
		return userDao.deleteUser(id);
	}	
}
