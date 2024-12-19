package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.theatre.model.Order;

@Repository
public class OrdersDaoImpl implements OrdersDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public Order findMyOrder(int userId) {
		System.out.println("jdbc find order by id " + userId);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT order_id, user_id, total_price, sale_date, status FROM ORDERS WHERE user_id =?");
			ps.setInt(1, userId);
			Order order = new Order();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setTotalPrice(rs.getDouble("total_price"));
					order.setSaleDate(rs.getDate("sale_date"));
					order.setStatus(rs.getString("status"));
					System.out.println(order.toString());
				}
				return order;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
