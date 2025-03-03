package com.a.demo.service;

import com.a.demo.model.Shelter;
import com.a.demo.repository.ShelterRepository;
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
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    // Create a new shelter
    public Shelter createShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    // Retrieve all shelters (non-paginated)
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    // Retrieve shelters with pagination and sorting
    public Page<Shelter> getSheltersWithPaginationAndSorting(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return shelterRepository.findAll(pageable);
    }

    // Retrieve a shelter by ID
    public Optional<Shelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    // Retrieve a shelter by name
    public Optional<Shelter> getShelterByName(String name) {
        return shelterRepository.findByName(name);
    }

    // Update shelter by ID using custom JPQL query and return updated shelter
    @Transactional
    public Shelter updateShelterById(Long id, Shelter updatedShelter) {
        int updatedRows = shelterRepository.updateShelterById(
                updatedShelter.getName(),
                updatedShelter.getAddress(),
                updatedShelter.getPhoneNumber(),
                updatedShelter.getEmail(),
                id
        );
        if (updatedRows > 0) {
            return shelterRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Shelter not found after update with ID: " + id));
        }
        throw new RuntimeException("Shelter not found with ID: " + id);
    }

    // Update shelter by name using custom JPQL query and return updated shelter
    @Transactional
    public Shelter updateShelterByName(String name, Shelter updatedShelter) {
        int updatedRows = shelterRepository.updateShelterByName(
                updatedShelter.getAddress(),
                updatedShelter.getPhoneNumber(),
                updatedShelter.getEmail(),
                name
        );
        if (updatedRows > 0) {
            return shelterRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Shelter not found after update with name: " + name));
        }
        throw new RuntimeException("Shelter not found with name: " + name);
    }

    // Delete shelter by ID using custom JPQL query
    @Transactional
    public String deleteShelterById(Long id) {
        int deletedRows = shelterRepository.deleteShelterById(id);
        return deletedRows > 0 ? "Shelter deleted successfully" : "Shelter not found";
    }

    // Delete shelter by name using custom JPQL query
    @Transactional
    public String deleteShelterByName(String name) {
        int deletedRows = shelterRepository.deleteShelterByName(name);
        return deletedRows > 0 ? "Shelter deleted successfully" : "Shelter not found";
    }
}

