package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pet_house.model.Cat;
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
		System.out.println("jdbc save User");
		User catExist = findUserById(user.getId());
		if (catExist != user) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO User(name) " + "VALUES(?)");
				ps.setString(1, user.getName());
				int i = ps.executeUpdate();

				if (i == 1) {
					System.out.println("jdbc saved User info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User findUserById(Integer id) {
		System.out.println("jdbc find user by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("SELECT id, name FROM User WHERE id =?");
			ps.setInt(1, id);
			User user = new User();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					System.out.println(user.toString());
				}
				return user;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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
