package com.tucymusic.app.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tucymusic.app.dao.OrderDao;
import com.tucymusic.app.model.Order;
import com.tucymusic.app.model.OrderItem;
import com.tucymusic.app.model.User;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public void create(Order order) {
		System.out.println("jdbc create order");
		Order ordertExist = findById(order.getOrderId());
		if (ordertExist != order) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO ORDERS(order_id, user_id, total_price, discount_percentage, sale_date, status) "
								+ "VALUES(?,?,?,?,?,?)");
				ps.setInt(1, order.getOrderId());
				ps.setInt(2, order.getUserId());
				ps.setBigDecimal(3, order.getTotalPrice());
				ps.setBigDecimal(4, order.getDiscount());
				ps.setDate(5, order.getSaleDate());
				ps.setString(6, order.getStatus());
				int i = ps.executeUpdate();
				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved order info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void update(Order order) {
		System.out.println("jdbc update order");
		Order target = findById(order.getOrderId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {

				PreparedStatement ps = connection.prepareStatement(
						"UPDATE ORDERS SET order_id=?, user_id=?, total_price=?, discount_percentage=?, sale_date=?, status=? WHERE order_id=?");
				ps.setInt(1, order.getOrderId());
				ps.setInt(2, order.getUserId());
				ps.setBigDecimal(3, order.getTotalPrice());
				ps.setBigDecimal(4, order.getDiscount());
				ps.setDate(5, order.getSaleDate());
				ps.setString(6, order.getStatus());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update order " + target.toString());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void remove(Order order) {
		System.out.println("jdbc delete Order");
		Order target = findById(order.getOrderId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM ORDERS WHERE order_id= ?");
				ps.setInt(1, order.getOrderId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete order " + target.toString());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void addOrderItem(Order order, OrderItem orderItem) {
		System.out.println("jdbc addOrderItem");
		Order orderExist = findById(order.getOrderId());
		if (orderExist != null) {
			try (Connection connection = dataSource.getConnection()) {
					PreparedStatement ps = connection
							.prepareStatement("INSERT INTO ORDER_ITEM(order_item_id, order_id, product_id, quantity, price) " + "VALUES(?,?,?,?,?)");
					ps.setInt(1, orderItem.getOrderItemId());
					ps.setInt(2, orderItem.getOrderId());
					ps.setInt(3, orderItem.getProductId());
					ps.setInt(4, orderItem.getQuantity());
					ps.setBigDecimal(5, orderItem.getPrice());
				int i = ps.executeUpdate();

				if (i == 1) {
					System.out.println("jdbc saved orderItem info to database");
					System.out.println(orderItem);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void removeOrderItem(Order order, OrderItem orderItem) {
		System.out.println("jdbc removeOrderItem");
		Order target = findById(order.getOrderId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM ORDER_ITEM WHERE order_item_id= ?");
				ps.setInt(1, orderItem.getOrderItemId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc removeOrderItem" + target.toString());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Order findById(int id) {
		System.out.println("jdbc find order by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT order_id, user_id, total_price, discount_percentage, sale_date, status FROM ORDERS WHERE order_id =?");
			ps.setInt(1, id);
			Order order = new Order();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setDiscount(rs.getBigDecimal("discount_percentage"));
					order.setSaleDate(rs.getDate("sale_date"));
					order.setStatus(rs.getString("status"));
					order.setOrderId(rs.getInt("order_id"));
					System.out.println(order);
				}
				return order;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> findAll() {
		System.out.println("jdbc find all Order");
		List<Order> orders = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT order_id, user_id, total_price, discount_percentage, sale_date, status FROM ORDERS");
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setTotalPrice(rs.getBigDecimal("total_price"));
				order.setDiscount(rs.getBigDecimal("discount_percentage"));
				order.setSaleDate(rs.getDate("sale_date"));
				order.setStatus(rs.getString("status"));
				orders.add(order);
			}
			System.out.println(orders);
			return orders;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> findByUser(User user) {
		System.out.println("jdbc find order by user " + user.toString());
		List<Order> orders = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT order_id, user_id, total_price, discount_percentage, sale_date, status FROM ORDERS WHERE user_id =?");
			ps.setInt(1, user.getId());
			Order order = new Order();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setDiscount(rs.getBigDecimal("discount_percentage"));
					order.setSaleDate(rs.getDate("sale_date"));
					order.setStatus(rs.getString("status"));
					order.setUserId(rs.getInt("user_id"));
					orders.add(order);
				}
				return orders;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> findByDate(Date date) {
		System.out.println("jdbc find order by date " + date.toString());
		List<Order> orders = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT order_id, user_id, total_price, discount_percentage, sale_date, status FROM ORDERS WHERE sale_date=?");
			ps.setDate(1, date);
			Order order = new Order();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setDiscount(rs.getBigDecimal("discount_percentage"));
					order.setSaleDate(rs.getDate("sale_date"));
					order.setStatus(rs.getString("status"));
					order.setSaleDate(rs.getDate("sale_date"));
					orders.add(order);
				}
				return orders;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> findByStatus(String status) {
		System.out.println("jdbc find order by status " + status);
		List<Order> orders = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT order_id, user_id, total_price, discount_percentage, sale_date, status FROM ORDERS WHERE status =?");
			ps.setString(1, status);
			Order order = new Order();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setTotalPrice(rs.getBigDecimal("total_price"));
					order.setDiscount(rs.getBigDecimal("discount_percentage"));
					order.setSaleDate(rs.getDate("sale_date"));
					order.setStatus(rs.getString("status"));
					order.setStatus(rs.getString("status"));
					orders.add(order);
				}
				return orders;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
