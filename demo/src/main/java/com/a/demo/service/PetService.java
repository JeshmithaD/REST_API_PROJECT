package com.a.demo.service;

import com.a.demo.model.Pet;
import com.a.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;

    // Create a new pet
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    // Get pet by ID
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    // Get pet by name
    public Optional<Pet> getPetByName(String name) {
        return petRepository.findByName(name);
    }

    // Get all pets (non-paginated)
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Get pets sorted by a specific field
    public List<Pet> sortPets(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return petRepository.findAll(sort);
    }

    // Get paginated pets
    public Page<Pet> getPaginatedPets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return petRepository.findAll(pageable);
    }

    // Get paginated and sorted pets
    public Page<Pet> getPaginatedSortedPets(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field));
        return petRepository.findAll(pageable);
    }

    // Update pet details by ID using custom JPQL query
    @Transactional
    public int updatePetById(Long id, Pet updatedPet) {
        return petRepository.updatePetById(
                updatedPet.getName(), // new name
                updatedPet.getSpecies(),
                updatedPet.getBreed(),
                updatedPet.getAge(),
                updatedPet.getHealthStatus(),
                updatedPet.getTemperament(),
                updatedPet.getAdoptionStatus(),
                updatedPet.getReviews(),
                id
        );
    }

    // Update pet details by name using custom JPQL query
    @Transactional
    public int updatePetByName(String name, Pet updatedPet) {
        return petRepository.updatePetByName(
                updatedPet.getSpecies(),
                updatedPet.getBreed(),
                updatedPet.getAge(),
                updatedPet.getHealthStatus(),
                updatedPet.getTemperament(),
                updatedPet.getAdoptionStatus(),
                updatedPet.getReviews(),
                name
        );
    }

    // Delete pet by ID
    public boolean deletePet(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Delete pet by name
    @Transactional
    public int deletePetByName(String name) {
        return petRepository.deleteByName(name);
    }
}
