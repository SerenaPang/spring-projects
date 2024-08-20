package com.tucymusic.app.dao;

import java.util.Date;
import java.util.List;

import com.tucymusic.app.model.Order;
import com.tucymusic.app.model.OrderItem;
import com.tucymusic.app.model.User;

public class JdbcOrderDao implements OrderDao{

	@Override
	public void create(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOrderItem(Order order, OrderItem orderItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOrderItem(Order order, OrderItem orderItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}
}
