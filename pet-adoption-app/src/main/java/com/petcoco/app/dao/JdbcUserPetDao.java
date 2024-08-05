package com.petcoco.app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petcoco.app.model.Record;

@Repository
public class JdbcUserPetDao implements UserPetAdoptionDao{
	@Autowired
	private JdbcDataSource dataSource;
	
	@Override
	public void saveAdoptionRecord(Record record) {
		
		
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
	public void deleteAdoptionRecord(Record record) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAdoptionRecord(Record record) {
		// TODO Auto-generated method stub
		
	}

}
