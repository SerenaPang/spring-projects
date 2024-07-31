package com.petcoco.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petcoco.app.model.User;

@Repository
public class JdbcUserDao implements UserDao{
	@Autowired
	private  JdbcDataSource dataSource;
	@Override
	public User saveUser(User user) {
		System.out.println("jdbc save user");
		User userExist = findUserById(user.getId());
		if (userExist != user) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO user(name, last_name) " + "VALUES(?,?)");
				ps.setString(1, user.getName());
				ps.setString(2, user.getLastName());
				int i = ps.executeUpdate();

				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved user info to database");
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
			PreparedStatement ps = connection.prepareStatement("SELECT id_user, name, last_name FROM USER WHERE id_user =?");
			ps.setInt(1, id);
			User user = new User();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					user.setId(rs.getInt("id_user"));
					user.setName(rs.getString("name"));
					user.setLastName(rs.getString("last_name"));
					System.out.println("id: " + user.getId() + " name: " + user.getName() + " last name: " + user.getLastName());
				}
				return user;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findAllUser() {
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
