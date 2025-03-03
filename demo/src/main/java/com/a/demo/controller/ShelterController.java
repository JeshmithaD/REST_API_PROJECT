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
        Shelter created = shelterService.createShelter(shelter);
        return ResponseEntity.status(201).body(created);
    }

    // Retrieve all shelters without pagination
    @GetMapping("/all")
    public ResponseEntity<List<Shelter>> getAllShelters() {
        List<Shelter> shelters = shelterService.getAllShelters();
        return ResponseEntity.ok(shelters);
    }

    // Retrieve shelters with pagination and sorting
    @GetMapping("/paginate")
    public ResponseEntity<Page<Shelter>> getSheltersWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        Page<Shelter> paginated = shelterService.getSheltersWithPaginationAndSorting(page, size, sortBy, sortOrder);
        return ResponseEntity.ok(paginated);
    }

    // Retrieve a shelter by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
        Optional<Shelter> shelter = shelterService.getShelterById(id);
        return shelter.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve a shelter by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Shelter> getShelterByName(@PathVariable String name) {
        Optional<Shelter> shelter = shelterService.getShelterByName(name);
        return shelter.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update shelter by ID (returns updated shelter)
    @PutMapping("/{id}")
    public ResponseEntity<Shelter> updateShelterById(@PathVariable Long id, @RequestBody Shelter updatedShelter) {
        try {
            Shelter updated = shelterService.updateShelterById(id, updatedShelter);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update shelter by name (returns updated shelter)
    @PutMapping("/name/{name}")
    public ResponseEntity<Shelter> updateShelterByName(@PathVariable String name, @RequestBody Shelter updatedShelter) {
        try {
            Shelter updated = shelterService.updateShelterByName(name, updatedShelter);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete shelter by ID and return a message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelterById(@PathVariable Long id) {
        String message = shelterService.deleteShelterById(id);
        return ResponseEntity.ok(message);
    }

    // Delete shelter by name and return a message
    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteShelterByName(@PathVariable String name) {
        String message = shelterService.deleteShelterByName(name);
        return ResponseEntity.ok(message);
    }
}
