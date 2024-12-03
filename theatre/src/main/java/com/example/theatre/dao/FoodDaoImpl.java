package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.theatre.model.Food;

@Repository
public class FoodDaoImpl implements FoodDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public List<Food> FindAllFood() {
		System.out.println("jdbc find all foods");
		List<Food> foods = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT food_id, name, price from Food");

			while (rs.next()) {
				Food food = new Food();
				food.setId(rs.getInt("food_id"));
				food.setName(rs.getString("name"));
				food.setPrice(rs.getDouble("price"));
				foods.add(food);
				System.out.println(food.toString());
			}
			System.out.println(foods);
			return foods;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return foods;
	}

}
