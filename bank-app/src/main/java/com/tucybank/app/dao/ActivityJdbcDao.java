package com.tucybank.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tucybank.app.model.Account;
import com.tucybank.app.model.Activity;

@Repository
public class ActivityJdbcDao implements ActivityDao {
	@Autowired
	private JdbcDataSource dataSource;
	@Autowired
	AccountJdbcDao accountJdbcDao;
	
	@Override
	public Activity saveActivity(Activity activity) {
		System.out.println("jdbc save Activity");
		Account accountExist = accountJdbcDao.findAccountById(activity.getIdAccount());
		
		if (accountExist != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO ACTIVITY(id_account, date_activity, type_activity, amount) " + "VALUES(?,?,?,?)");
				ps.setInt(1, activity.getIdAccount());
				ps.setDate(2, activity.getDate());
				ps.setString(3, activity.getType());
				ps.setFloat(4, activity.getAmount());
				int i = ps.executeUpdate();

				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved Activity info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public Activity saveActivity(Integer idAccount, Activity activity) {
		System.out.println("jdbc save Activity");
		Account accountExist = accountJdbcDao.findAccountById(idAccount);	
		if (accountExist != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO ACTIVITY(id_account, date_activity, type_activity, amount) " + "VALUES(?,?,?,?)");
				ps.setInt(1, activity.getIdAccount());
				ps.setDate(2, activity.getDate());
				ps.setString(3, activity.getType());
				ps.setFloat(4, activity.getAmount());
				int i = ps.executeUpdate();

				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved Activity info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public Activity findActivityById(Integer id) {
		System.out.println("jdbc find Activity by id " + id);

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT id_activity, id_account, date_activity, type_activity, amount FROM ACTIVITY WHERE id_activity =?");
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
			ResultSet rs = stmt
					.executeQuery("SELECT id_activity, id_account, date_activity, type_activity, amount from ACTIVITY");

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
		return activities;
	}

	@Override
	public Activity deleteActivity(Integer idActivity) {
		System.out.println("jdbc delete Activity");

		Activity target = findActivityById(idActivity);
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM ACTIVITY WHERE id_activity= ?");
				ps.setInt(1, idActivity);
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete Activity " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Activity updateActivity(Activity activity) {
		System.out.println("jdbc update activity");
		Activity target = findActivityById(activity.getIdActivity());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement(
						"UPDATE ACTIVITY SET id_account=? date_activity=? type_activity=? amount=? WHERE id_activity=?");
				ps.setInt(1, activity.getIdAccount());
				ps.setDate(2, activity.getDate());
				ps.setString(3, activity.getType());
				ps.setFloat(4, activity.getAmount());
				ps.setInt(5, activity.getIdActivity());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update activity " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
