package com.tucyticket.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tucyticket.app.model.User;

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
				PreparedStatement ps = connection.prepareStatement("INSERT INTO user(name_user) " + "VALUES(?)");
				ps.setString(1, user.getName());
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
			PreparedStatement ps = connection.prepareStatement("SELECT id_user, name_user FROM USER WHERE id_user =?");
			ps.setInt(1, id);
			User user = new User();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					user.setId(rs.getInt("id_user"));
					user.setName(rs.getString("name_user"));
					System.out.println("id: " + user.getId() + " name: " + user.getName());
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
		System.out.println("jdbc find all users");
		List<User> users = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id_user, name_user from USER");

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id_user"));
				user.setName(rs.getString("name_user"));
				users.add(user);
			}
			System.out.println(users);
			return users;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return users;
	}

	@Override
	public User deleteUser(Integer idUser) {
		System.out.println("jdbc delete User");

		User target = findUserById(idUser);
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM User WHERE id_user= ?");
				ps.setInt(1, idUser);
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete User " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User updateUser(User user) {
		System.out.println("jdbc update user");
		User target = findUserById(user.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE User SET name_user=? WHERE id_user=?");
				ps.setString(1, user.getName());
				ps.setInt(2, user.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update user " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
