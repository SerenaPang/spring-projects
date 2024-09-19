package com.musictemplate.app.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.musictemplate.app.model.Genre;
import com.musictemplate.app.model.Order;
import com.musictemplate.app.model.OrderItem;
import com.musictemplate.app.model.Product;
import com.musictemplate.app.model.ProductType;
import com.musictemplate.app.model.User;

@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcOperations.html#queryForObject(java.lang.String,org.springframework.jdbc.core.RowMapper,java.lang.Object...)
	static class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getInt(1));
			product.setProductType(rs.getInt(2));
			product.setName(rs.getString(3));
			product.setGenreId(rs.getInt(4));
			product.setPrice(rs.getBigDecimal(5));
			return product;
		}
	}

	static class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			return user;
		}
	}

	static class ProductTypeRowMapper implements RowMapper<ProductType> {
		@Override
		public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductType productType = new ProductType();
			productType.setId(rs.getInt(1));
			productType.setDescription((rs.getString(2)));
			return productType;
		}
	}

	static class GenreRowMapper implements RowMapper<Genre> {
		@Override
		public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
			Genre genre = new Genre();
			genre.setId(rs.getInt(1));
			genre.setGenre(rs.getString(2));
			return genre;
		}
	}

	static class OrdersRowMapper implements RowMapper<Order> {
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setOrderId(rs.getInt(1));
			order.setUserId(rs.getInt(2));
			order.setTotalPrice(rs.getBigDecimal(3));
			order.setDiscount(rs.getBigDecimal(4));
			order.setSaleDate(rs.getDate(5));
			order.setStatus(rs.getString(6));
			return order;
		}
	}

	static class OrderItemRowMapper implements RowMapper<OrderItem> {
		@Override
		public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderItemId(rs.getInt(1));
			orderItem.setOrderId(rs.getInt(2));
			orderItem.setProductId(rs.getInt(3));
			orderItem.setQuantity(rs.getInt(4));
			orderItem.setPrice(rs.getBigDecimal(5));
			return orderItem;
		}
	}

	public Product findProductById(Long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject(
				"select product_id, product_type_id, name, genre_id, price from Product where product_id = :product_id",
				namedParameters, new ProductRowMapper());
	}

	public List<Product> findAllProducts() {
		return this.jdbcTemplate.query("select product_id, product_type_id, name, genre_id, price from Product",
				new ProductRowMapper());
	}

	public List<Product> getAllProducts() {
		return jdbcTemplate.query("select product_id, product_type_id, name, genre_id, price from Product",
				new ProductRowMapper());
	}

	public void save(Product product) {
		jdbcTemplate.update("insert into Product(product_id, product_type_id, name, genre_id, price) values(?,?,?,?,?)",
				product.getId(), product.getProductType(), product.getName(), product.getGenreId(), product.getPrice());
	}

	public void update(Product product) {
		jdbcTemplate.update(
				"update Product set product_id =?, product_type_id=?, name=?, genre_id=?, price = ? where product_id = ?",
				product.getId(), product.getProductType(), product.getName(), product.getGenreId(), product.getPrice(),
				product.getId());
	}

	public void delete(int productId) {
		jdbcTemplate.update("delete from Product where product_id = ?", productId);
	}

	public User findUserById(Long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject("select user_id,name_user from User where user_id = :user_id",
				namedParameters, new UserRowMapper());
	}

	public List<User> findAllUsers() {
		return this.jdbcTemplate.query("select user_id, name_user from User", new UserRowMapper());
	}

	public List<User> getAllUsers() {
		return jdbcTemplate.query("select user_id, name_user from User", new UserRowMapper());
	}

	public void saveUser(User user) {
		jdbcTemplate.update("insert into User(user_id, name_user) values(?,?)", user.getId(), user.getName());
	}

	public void updateUser(User user) {
		jdbcTemplate.update("update User set user_id =?, name_user=? where user_id = ?", user.getId(), user.getName(),
				user.getId());
	}

	public void deleteUser(int userId) {
		jdbcTemplate.update("delete from User where user_id = ?", userId);
	}

	public ProductType findProductTypeById(Long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject(
				"select product_type_id, description from ProductType where product_type_id = :product_type_id",
				namedParameters, new ProductTypeRowMapper());
	}

	public List<ProductType> findAllProductTypes() {
		return this.jdbcTemplate.query("select product_type_id, description from ProductType",
				new ProductTypeRowMapper());
	}

	public List<ProductType> getAllProductTypes() {
		return jdbcTemplate.query("select product_type_id, description from ProductType", new ProductTypeRowMapper());
	}

	public void saveProductType(ProductType productType) {
		jdbcTemplate.update("insert into ProductType(product_type_id, description) values(?,?)", productType.getId(),
				productType.getDescription());
	}

	public void updateProductType(ProductType productType) {
		jdbcTemplate.update("update ProductType set product_type_id=?, description=? where product_type_id = ?",
				productType.getId(), productType.getDescription(), productType.getId());
	}

	public void deleteProductType(int productTypeId) {
		jdbcTemplate.update("delete from ProductType where product_type_id = ?", productTypeId);
	}

	public Genre findGenreById(Long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject("select genre_id, genre from Genre where genre_id = :genre_id",
				namedParameters, new GenreRowMapper());
	}

	public List<Genre> findAllGenres() {
		return this.jdbcTemplate.query("select genre_id, genre from Genre", new GenreRowMapper());
	}

	public List<Genre> getAllGenres() {
		return jdbcTemplate.query("select genre_id, genre from Genre", new GenreRowMapper());
	}

	public void saveGenre(Genre genre) {
		jdbcTemplate.update("insert into Genre(genre_id, genre) values(?,?)", genre.getId(), genre.getGenre());
	}

	public void updateGenre(Genre genre) {
		jdbcTemplate.update("update Genre set genre_id=?, genre=? where genre_id = ?", genre.getId(), genre.getGenre(),
				genre.getId());
	}

	public void deleteGenre(int genreId) {
		jdbcTemplate.update("delete from Genre where genre_id = ?", genreId);
	}
	
	public Order findOrdersById(Long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject("select order_id, user_id, total_price, discount_percentage, sale_date, status from Orders where order_id = :order_id",
				namedParameters, new OrdersRowMapper());
	}

	public List<Order> findAllOrders() {
		return this.jdbcTemplate.query("select order_id, user_id, total_price, discount_percentage, sale_date, status from Orders", new OrdersRowMapper());
	}

	public List<Order> getAllOrders() {
		return jdbcTemplate.query("select order_id, user_id, total_price, discount_percentage, sale_date, status from Orders", new OrdersRowMapper());
	}

	public void saveOrders(Order order) {
		jdbcTemplate.update("insert into Orders(order_id, user_id, total_price, discount_percentage, sale_date, status) values(?,?,?,?,?,?)", order.getOrderId(), order.getUserId(),order.getTotalPrice(), order.getDiscount(), order.getSaleDate(), order.getStatus());
	}

	public void updateOrders(Order order) {
		jdbcTemplate.update("update Orders set order_id=?, user_id=?, total_price=?, discount_percentage=?, sale_date = ? status=?, genre=? where order_id = ?", order.getOrderId(), order.getUserId(),order.getTotalPrice(), order.getDiscount(), order.getSaleDate(), order.getStatus() ,order.getOrderId());
	}

	public void deleteOrders(int orderId) {
		jdbcTemplate.update("delete from Orders where order_id = ?", orderId);
	}
	
	public OrderItem findOrderItemById(Long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject("select order_item_id, order_id, product_id, quantity, price from OrderItem where order_item_id = :order_item_id",
				namedParameters, new OrderItemRowMapper());
	}

	public List<OrderItem> findAllOrderItem() {
		return this.jdbcTemplate.query("select order_item_id, order_id, product_id, quantity, price from OrderItem", new OrderItemRowMapper());
	}

	public List<OrderItem> getAllOrderItem() {
		return jdbcTemplate.query("select order_item_id, order_id, product_id, quantity, price  from OrderItem", new OrderItemRowMapper());
	}

	public void saveOrderItem(OrderItem orderItem) {
		jdbcTemplate.update("insert into OrderItem(order_item_id, order_id, product_id, quantity, price) values(?,?,?,?,?)", orderItem.getOrderItemId(), orderItem.getOrderId(), orderItem.getProductId(), orderItem.getQuantity(), orderItem.getPrice());
	}

	public void updateOrderItem(OrderItem orderItem) {
		jdbcTemplate.update("update OrderItem set order_item_id=?, order_id=?, product_id=?, quantity=?, price =? where order_item_id = ?", orderItem.getOrderItemId(), orderItem.getOrderId(), orderItem.getProductId(), orderItem.getQuantity(), orderItem.getPrice(), orderItem.getOrderItemId());
	}

	public void deleteOrderItem(int orderItemId) {
		jdbcTemplate.update("delete from OrderItem where order_item_id = ?", orderItemId);
	}
}
