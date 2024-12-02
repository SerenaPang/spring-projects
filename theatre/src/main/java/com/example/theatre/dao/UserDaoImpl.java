package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.theatre.model.User;

/**
 * This class does save, find user by id, find all users, delete user, update
 * user in mysql database.
 */
@Repository
public class UserDaoImpl implements UserDao {
	// TODO: Use Spring JDBC template
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public User saveUser(User user) {
		System.out.println("jdbc save User");
		User userExist = findUserById(user.getId());
		if (userExist != user) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO User(user_name, user_password) " + "VALUES(?,?)");
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
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
			PreparedStatement ps = connection.prepareStatement("SELECT user_id, user_name FROM User WHERE user_id =?");
			ps.setInt(1, id);
			User user = new User();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					user.setId(rs.getInt("user_id"));
					user.setName(rs.getString("user_name"));
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
		System.out.println("jdbc find all users");
		List<User> users = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT user_id, user_name from User");

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
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
		System.out.println("jdbc delete user");
		User target = findUserById(idUser);
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM User WHERE user_id= ?");
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
		System.out.println("jdbc update User");
		User target = findUserById(user.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection
						.prepareStatement("UPDATE User SET user_id=?, user_name=?, user_password=? WHERE user_id=?");
				ps.setInt(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				ps.setInt(4, user.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update User " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean validUserAndPassword(String user, String password) {
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT user_id FROM User WHERE user_name =? and user_password = ?");
			ps.setString(1, user);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					// One row was found.
					return true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		// User and password were not found.
		return false;
	}
}
