package com.a.demo.controller;

import com.a.demo.model.Shelter;
import com.a.demo.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shelters")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    // Create a new shelter
    @PostMapping
    public ResponseEntity<Shelter> createShelter(@RequestBody Shelter shelter) {
        return ResponseEntity.status(201).body(shelterService.createShelter(shelter));
    }

    // Get all shelters
    @GetMapping
    public ResponseEntity<List<Shelter>> getAllShelters() {
        return ResponseEntity.ok(shelterService.getAllShelters());
    }

    // Get shelter by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
        Optional<Shelter> shelter = shelterService.getShelterById(id);
        return shelter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get shelter by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Shelter> getShelterByName(@PathVariable String name) {
        Optional<Shelter> shelter = shelterService.getShelterByName(name);
        return shelter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update shelter details
    @PutMapping("/{id}")
    public ResponseEntity<Shelter> updateShelter(@PathVariable Long id, @RequestBody Shelter updatedShelter) {
        return ResponseEntity.ok(shelterService.updateShelter(id, updatedShelter));
    }

    // Delete shelter by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.noContent().build();
    }

    // Pagination and sorting
    @GetMapping("/paginate")
    public ResponseEntity<Page<Shelter>> getSheltersWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return ResponseEntity.ok(shelterService.getSheltersWithPaginationAndSorting(page, size, sortBy, sortOrder));
    }
}
