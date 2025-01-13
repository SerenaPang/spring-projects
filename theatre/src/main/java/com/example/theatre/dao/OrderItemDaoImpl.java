package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.theatre.model.OrderItem;

@Repository
public class OrderItemDaoImpl implements OrderItemDao{
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public List<OrderItem> findAllMyOrderItems(int orderId) {
		System.out.println("jdbc find all user's one order's order items");
		List<OrderItem> orderItems = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT order_item_id, order_id, ticket_id, food_id, drink_id, quantity, price from ORDER_ITEM");

			while (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderItemId(rs.getInt(""));
				orderItem.setOrderId(rs.getInt(""));
				orderItem.setTicket(null);
								
				orderItems.add(orderItem);
			}
			System.out.println(orderItems);
			return orderItems;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return orderItems;
	}

}
