package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.theatre.model.Drink;


@Repository
public class DrinkDaoImpl implements DrinkDao{
	@Autowired
	private JdbcDataSource dataSource;
	
	@Override
	public List<Drink> findAllDrinks() {
		System.out.println("jdbc find all drinks");
		List<Drink> drinks = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, price from Drink");

			while (rs.next()) {
				Drink drink = new Drink();
				drink.setId(rs.getInt("id"));
				drink.setName(rs.getString("name"));
				drink.setPrice(rs.getDouble("price"));
				drinks.add(drink);
				System.out.println(drink.toString());
			}
			System.out.println(drinks);
			return drinks;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return drinks;
	}
}
