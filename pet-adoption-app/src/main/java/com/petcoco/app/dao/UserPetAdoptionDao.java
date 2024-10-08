package com.petcoco.app.dao;

import java.util.List;

import com.petcoco.app.model.Record;

public interface UserPetAdoptionDao {
	public Record saveAdoptionRecord(Record record );
	public List<Record> findAllAdoptionRecord();
	public Record findRecordByUserId(Integer idUser);
	public Record deleteAdoptionRecord(Record record);
	public Record updateAdoptionRecord(Record record);
}
