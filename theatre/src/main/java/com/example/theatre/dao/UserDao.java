package com.example.theatre.dao;

import java.util.List;
import com.example.theatre.model.User;

public interface UserDao {
	public User saveUser(User user);
	public User findUserById(Integer id);
	public List<User> findAllUsers();
	public User deleteUser(Integer idUser);
	public User updateUser(User user);
	public boolean validUserAndPassword(String user, String password);
	public User findUserByName(String username);
}
