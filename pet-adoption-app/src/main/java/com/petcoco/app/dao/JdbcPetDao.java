package com.petcoco.app.dao;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet findPetById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> findAllPets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet deletePet(Integer idPet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet updatePet(Pet pet) {
		// TODO Auto-generated method stub
		return null;
	}

}
