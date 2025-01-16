package com.example.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.example.theatre.dao.OrdersDao;
import com.example.theatre.model.Order;

@Service
@ApplicationScope
public class OrderService {
	@Autowired
	private OrdersDao orderDao;
	
	public Order findMyOrder(int userId) {
		return orderDao.findMyOrder(userId);
	}
}
