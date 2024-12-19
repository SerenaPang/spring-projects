package com.example.theatre.dao;

import com.example.theatre.model.Order;

public interface OrdersDao {
	public Order findMyOrder(int userId);
}
