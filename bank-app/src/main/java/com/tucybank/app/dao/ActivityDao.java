package com.tucybank.app.dao;

import java.util.List;

import com.tucybank.app.model.Activity;

public interface ActivityDao {
	public Activity saveActivity(Activity activity);
	public Activity findActivityById(Integer id);
	public List<Activity> findAllActivities();
	public Activity deleteActivity(Integer idActivity);
	public Activity updateActivity(Activity activity);
}
