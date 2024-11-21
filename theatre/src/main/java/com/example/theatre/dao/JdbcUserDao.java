package com.example.theatre.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.theatre.model.User;
/**
 * This class does save, find user by id, find all users, delete user, update user in mysql database. 
 * */
@Repository
public class JdbcUserDao implements UserDao{
	@Autowired
	private JdbcDataSource dataSource;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
