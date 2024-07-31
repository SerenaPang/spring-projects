package com.petcoco.dao;

import java.util.List;

import com.petcoco.model.User;

public interface UserDao {
	public User saveUser(User user);
	public User findUserById(Integer id);
	public List<User> findAllUser();
	public User deleteUser(Integer idUser);
	public User updateUser(User user);
}
