package com.tucybank.app.dao;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> findAllActivities() {
		// TODO Auto-generated method stub
		return null;
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
