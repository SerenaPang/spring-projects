package com.petcoco.dao;

import java.util.List;

import com.petcoco.model.Pet;

public interface PetDao {
	public Pet savePet(Pet pet);
	public Pet findPetById(Integer id);
	public List<Pet> findAllPets();
	public Pet deletePet(Integer idPet);
	public Pet updatePet(Pet pet);
}
