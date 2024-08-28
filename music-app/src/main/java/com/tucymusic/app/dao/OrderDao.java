package com.tucymusic.app.dao;


import java.sql.Date;
import java.util.List;

import com.tucymusic.app.model.Order;
import com.tucymusic.app.model.OrderItem;
import com.tucymusic.app.model.User;

public interface OrderDao {

	public void create(Order order);
	public void update(Order order);
	public void remove(int orderId);
	public void addOrderItem(int orderId, OrderItem orderItem);
	public void removeOrderItem(int orderId, OrderItem orderItem);
	public Order findById(int id);
	public List<Order> findAll();
	public List<Order> findByUser(User user);
	public List<Order> findByDate(Date date);
	public List<Order> findByStatus(String status);
	
}
