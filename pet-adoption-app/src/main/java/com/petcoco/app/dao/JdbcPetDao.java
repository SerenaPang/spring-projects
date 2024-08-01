package com.petcoco.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petcoco.app.model.Pet;

@Repository
public class JdbcPetDao implements PetDao{
	@Autowired
	private  JdbcDataSource dataSource;
	
	@Override
	public Pet savePet(Pet pet) {
		System.out.println("jdbc save pet");
		Pet petExist = findPetById(pet.getId());
		if (petExist != pet) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Pet(name, id_type, age, status) " + "VALUES(?,?,?,?)");
				ps.setString(1, pet.getName());
				ps.setInt(2, pet.getIdType());
				ps.setInt(3, pet.getAge());
				ps.setString(4, pet.getStatus());
				int i = ps.executeUpdate();

				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved pet info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Pet findPetById(Integer id) {
		System.out.println("jdbc find Pet by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT id_pet, name, id_type, age, status FROM Pet WHERE id_pet =?");
			ps.setInt(1, id);
			Pet pet = new Pet();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					pet.setId(rs.getInt("id_pet"));
					pet.setName(rs.getString("name"));
					pet.setIdType(rs.getInt("id_type"));
					pet.setAge(rs.getInt("age"));
					pet.setStatus(rs.getString("status"));
					System.out.println(pet.toString());
				}
				return pet;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Pet> findAllPets() {
		System.out.println("jdbc find all pets");
		List<Pet> pets = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id_pet, name, id_type, age, status from Pet");

			while (rs.next()) {
				Pet pet = new Pet();
				pet.setId(rs.getInt("id_pet"));
				pet.setName(rs.getString("name"));
				pet.setIdType(rs.getInt("id_type"));
				pet.setAge(rs.getInt("age"));
				pet.setStatus(rs.getString("status"));
				pets.add(pet);
			}
			System.out.println(pets);
			return pets;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return pets;
	}

	@Override
	public Pet deletePet(Integer idPet) {
		System.out.println("jdbc delete pet");

		Pet target = findPetById(idPet);
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM Pet WHERE id_pet= ?");
				ps.setInt(1, idPet);
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
	public Pet updatePet(Pet pet) {
		System.out.println("jdbc update pet");
		Pet target = findPetById(pet.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE Pet SET name=?, id_type=?, age=?, status=? WHERE id_pet=?");
				ps.setString(1, pet.getName());
				ps.setInt(2, pet.getIdType());
				ps.setInt(3, pet.getAge());
				ps.setString(4, pet.getStatus());
				ps.setInt(5, pet.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update pet " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

}
