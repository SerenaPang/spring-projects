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
import com.petcoco.app.model.Record;

@Repository
public class JdbcUserPetDao implements UserPetAdoptionDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Autowired
	JdbcPetDao petDao;

	@Override
	public Record saveAdoptionRecord(Record record) {
		// check if pet exist and available for adoption
		System.out.println("jdbc save record");
		Pet petExist = petDao.findPetById(record.getIdPet());
		if (petExist != null && !petExist.getStatus().equals("Adopted")) {

			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO user_pet(id_user, id_pet, adoption_date) " + "VALUES(?,?,?)");
				ps.setInt(1, record.getIdUser());
				ps.setInt(2, record.getIdPet());
				ps.setDate(3, record.getDate());
				int i = ps.executeUpdate();

				if (i == 1) {
					System.out.println("jdbc saved adoption record info to database");
					System.out.println(record.toString());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			// update status of the pet to be adopted
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE Pet SET name=?, id_type=?, age=?, status=? WHERE id_pet=?");
				ps2.setString(1, petExist.getName());
				ps2.setInt(2, petExist.getIdType());
				ps2.setInt(3, petExist.getAge());
				ps2.setString(4, "Adopted");
				ps2.setInt(5, petExist.getId());
				int i = ps2.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update pet " + petExist.toString());
					return record;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Pet is not available for adoption");
			return null;
		}
		return null;
	}

	@Override
	public Record findRecordByUserId(Integer idUser) {
		System.out.println("jdbc find Record by user id");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("SELECT id_user, id_pet, adoption_date from user_pet WHERE id_user =?");
			ps.setInt(1, idUser);
			Record record = new Record();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					record.setIdUser(rs.getInt("id_user"));
					record.setIdPet(rs.getInt("id_pet"));
					record.setDate(rs.getDate("adoption_date"));
				}
			}
			System.out.println(record.toString());
			return record;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Record> findAllAdoptionRecord() {
		System.out.println("jdbc find all records");
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id_user, id_pet, adoption_date from user_pet");
			List<Record> records = new ArrayList<>();
			while (rs.next()) {
				Record record = new Record();
				record.setIdUser(rs.getInt("id_user"));
				record.setIdPet(rs.getInt("id_pet"));
				record.setDate(rs.getDate("adoption_date"));

				records.add(record);
			}
			System.out.println(records);
			return records;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Record deleteAdoptionRecord(Record record) {
		System.out.println("jdbc delete record");

		if (record != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM user_pet WHERE id_user= ?");
				ps.setInt(1, record.getIdUser());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete record " + record.toString());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			// update status of the pet to be available for adoption
			try (Connection connection = dataSource.getConnection()) {
				Integer idPet = record.getIdPet();
				PreparedStatement ps2 = connection
						.prepareStatement("UPDATE Pet SET status=? WHERE id_pet=?");		
				ps2.setString(1, "Available");
				ps2.setInt(2, idPet);
				int i = ps2.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update status pet id: " + idPet);
					return record;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Record updateAdoptionRecord(Record record) {
		System.out.println("jdbc update record");
		Record recordExist = findRecordByUserId(record.getIdUser());
		if (recordExist != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE user_pet SET id_user=? id_pet=? date=? WHERE id_user=?");
				ps.setInt(1, record.getIdUser());
				ps.setInt(2, record.getIdPet());
				ps.setDate(3, record.getDate());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update record " + record.toString());
					return record;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
