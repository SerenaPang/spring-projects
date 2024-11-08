package com.example.pet_house.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pet_house.model.Cat;

@Repository
public class JdbcCatDao implements CatDao{
	@Autowired
	private  JdbcDataSource dataSource;

	@Override
	public Cat saveCat(Cat cat) {
		System.out.println("jdbc save Cat");
		Cat catExist = findCatById(cat.getId());
		if (catExist != cat) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Pet(name, age, breed, description) " + "VALUES(?,?,?,?)");
				ps.setString(1, cat.getName());
				ps.setInt(2, cat.getAge());
				ps.setString(3, cat.getBreed());
				ps.setString(4, cat.getDescription());
				int i = ps.executeUpdate();

				if (i == 1) {
					System.out.println("jdbc saved Cat info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Cat findCatById(Integer id) {
		System.out.println("jdbc find cat by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT id_pet, name, age, breed, description FROM Pet WHERE id_pet =?");
			ps.setInt(1, id);
			Cat cat = new Cat();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					cat.setId(rs.getInt("id_pet"));
					cat.setName(rs.getString("name"));
					cat.setAge(rs.getInt("age"));
					cat.setBreed(rs.getString("breed"));
					cat.setDescription(rs.getString("description"));
					cat.setId(rs.getInt("id_pet"));
					System.out.println(cat.toString());
				}
				return cat;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cat> findAllCats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cat deleteCat(Integer idCat) {
		System.out.println("jdbc delete cat");

		Cat target = findCatById(idCat);
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM Pet WHERE id_pet= ?");
				ps.setInt(1, idCat);
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete Pet " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Cat updateCat(Cat cat) {
		System.out.println("jdbc update Cat");
		Cat target = findCatById(cat.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE Pet SET name=?, age=?, breed=?, description=? WHERE id_pet=?");
				ps.setString(1, cat.getName());
				ps.setInt(2, cat.getAge());
				ps.setString(3, cat.getBreed());
				ps.setString(4, cat.getDescription());
				ps.setInt(5, cat.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update Cat " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
