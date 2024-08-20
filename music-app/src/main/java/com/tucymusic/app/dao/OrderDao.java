package com.tucymusic.app.dao;

import java.util.Date;
import java.util.List;

import com.tucymusic.app.model.Order;
import com.tucymusic.app.model.OrderItem;
import com.tucymusic.app.model.User;

public interface OrderDao {

	public void create(Order order);
	public void update(Order order);
	public void remove(Order order);
	public void addOrderItem(Order order, OrderItem orderItem);
	public void removeOrderItem(Order order, OrderItem orderItem);
	public Order findById(long id);
	public List<Order> findAll();
	public List<Order> findByUser(User user);
	public List<Order> findByDate(Date date);
	public List<Order> findByStatus(String status);
	
}
