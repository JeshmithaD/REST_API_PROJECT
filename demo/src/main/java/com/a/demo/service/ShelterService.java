package com.a.demo.service;


import com.a.demo.model.Shelter;
import com.a.demo.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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

    // Get all shelters
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    // Get shelter by ID
    public Optional<Shelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    // Get shelter by name
    public Optional<Shelter> getShelterByName(String name) {
        return shelterRepository.findByName(name);
    }

    // Update shelter details
    public Shelter updateShelter(Long id, Shelter updatedShelter) {
        return shelterRepository.findById(id).map(shelter -> {
            shelter.setName(updatedShelter.getName());
            shelter.setLocation(updatedShelter.getLocation());
            shelter.setCapacity(updatedShelter.getCapacity());
            return shelterRepository.save(shelter);
        }).orElseThrow(() -> new RuntimeException("Shelter not found"));
    }

    // Delete shelter by ID
    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id);
    }

    // Pagination and Sorting
    public Page<Shelter> getSheltersWithPaginationAndSorting(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return shelterRepository.findAll(pageable);
    }
}
