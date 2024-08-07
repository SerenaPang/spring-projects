package com.tucybank.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tucybank.app.model.Activity;

public class ActivityJdbcDao implements ActivityDao{
	@Autowired
	private JdbcDataSource dataSource;
	
	@Override
	public Activity saveActivity(Activity activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity findActivityById(Integer id) {
		System.out.println("jdbc find Activity by id " + id);

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT id_activity, id_account, date_activity, type_activity, amount FROM ACTIVITY WHERE id_activity =?");
			ps.setInt(1, id);
			Activity activity = new Activity();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					activity.setIdActivity(rs.getInt("id_activity"));
					activity.setIdAccount(rs.getInt("id_account"));
					activity.setDate(rs.getDate("date_activity"));
					activity.setType(rs.getString("type_activity"));
					activity.setAmount(rs.getFloat("amount"));			
					System.out.println(activity.toString());
				}
				return activity;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Activity> findAllActivities() {
		System.out.println("jdbc find all authors");
		List<Activity> activities = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name from ACTIVITY");

			while (rs.next()) {
				Activity activity = new Activity();
				activity.setIdActivity(rs.getInt("id_activity"));
				activity.setIdAccount(rs.getInt("id_account"));
				activity.setDate(rs.getDate("date_activity"));
				activity.setType(rs.getString("type_activity"));
				activity.setAmount(rs.getFloat("amount"));	
				activities.add(activity);
			}
			System.out.println(activities);
			return activities;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return authors;
	}

	@Override
	public Activity deleteActivity(Integer idActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		return null;
	}

}
