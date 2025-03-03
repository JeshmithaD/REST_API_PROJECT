package com.a.demo.service;

import com.a.demo.model.Vet;
import com.a.demo.repository.VetRepository;
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
public class VetService {

    @Autowired
    private VetRepository vetRepository;

    // Create a new vet
    public Vet createVet(Vet vet) {
        return vetRepository.save(vet);
    }

    // Retrieve all vets (non-paginated)
    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }

    // Retrieve vets with pagination and sorting
    public Page<Vet> getVetsWithPaginationAndSorting(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return vetRepository.findAll(pageable);
    }

    // Retrieve a vet by ID
    public Optional<Vet> getVetById(Long id) {
        return vetRepository.findById(id);
    }

    // Retrieve a vet by name
    public Optional<Vet> getVetByName(String name) {
        return vetRepository.findByName(name);
    }

    // Update vet by ID using custom JPQL query
    @Transactional
    public Vet updateVetById(Long id, Vet updatedVet) {
        int updatedRows = vetRepository.updateVetById(
                updatedVet.getName(),
                updatedVet.getClinicInfo(),
                updatedVet.getContactInfo(),
                updatedVet.getPetId(),
                id
        );
        if (updatedRows > 0) {
            return vetRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vet not found after update with ID: " + id));
        }
        throw new RuntimeException("Vet not found with ID: " + id);
    }

    // Update vet by name using custom JPQL query
    @Transactional
    public Vet updateVetByName(String name, Vet updatedVet) {
        int updatedRows = vetRepository.updateVetByName(
                updatedVet.getClinicInfo(),
                updatedVet.getContactInfo(),
                updatedVet.getPetId(),
                name
        );
        if (updatedRows > 0) {
            return vetRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Vet not found after update with name: " + name));
        }
        throw new RuntimeException("Vet not found with name: " + name);
    }

    // Delete vet by ID using custom JPQL query
    @Transactional
    public String deleteVetById(Long id) {
        int deletedRows = vetRepository.deleteVetById(id);
        return deletedRows > 0 ? "Vet deleted successfully" : "Vet not found";
    }

    // Delete vet by name using custom JPQL query
    @Transactional
    public String deleteVetByName(String name) {
        int deletedRows = vetRepository.deleteVetByName(name);
        return deletedRows > 0 ? "Vet deleted successfully" : "Vet not found";
    }
}

