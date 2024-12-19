package com.example.theatre.dao;

import java.util.List;

import com.example.theatre.model.OrderItem;

public interface OrderItemDao {
	public List<OrderItem> findAllMyOrderItems(int userId);
}
