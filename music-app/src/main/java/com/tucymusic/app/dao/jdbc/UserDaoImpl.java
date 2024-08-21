package com.tucymusic.app.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tucymusic.app.dao.UserDao;
import com.tucymusic.app.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public void create(User user) {
		System.out.println("jdbc create user");
		User userExist = findById(user.getId());
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
	}

	@Override
	public void update(User user) {
		System.out.println("jdbc update user");
		User target = findById(user.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE User SET name_user=? WHERE user_id=?");
				ps.setString(1, user.getName());
				ps.setInt(2, user.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update user " + target.toString());
				}
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public User findById(Integer id) {
		System.out.println("jdbc find user by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT user_id, name_user FROM USER WHERE user_id =?");
			ps.setInt(1, id);
			User user = new User();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					user.setId(rs.getInt("user_id"));
					user.setName(rs.getString("name_user"));
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
	public List<User> findAll() {
		System.out.println("jdbc find all users");
		List<User> users = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT user_id, name_user from USER");

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
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

}
