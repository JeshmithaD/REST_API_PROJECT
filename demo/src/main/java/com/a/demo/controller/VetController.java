package com.a.demo.controller;

import com.a.demo.model.Vet;
import com.a.demo.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vets")
public class VetController {

    @Autowired
    private VetService vetService;

    // Create a new vet
    @PostMapping
    public ResponseEntity<Vet> createVet(@RequestBody Vet vet) {
        Vet created = vetService.createVet(vet);
        return ResponseEntity.status(201).body(created);
    }

    // Retrieve all vets (non-paginated)
    @GetMapping("/all")
    public ResponseEntity<List<Vet>> getAllVets() {
        List<Vet> vets = vetService.getAllVets();
        return ResponseEntity.ok(vets);
    }

    // Retrieve vets with pagination and sorting
    @GetMapping("/paginate")
    public ResponseEntity<Page<Vet>> getVetsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "vetId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        Page<Vet> paginated = vetService.getVetsWithPaginationAndSorting(page, size, sortBy, sortOrder);
        return ResponseEntity.ok(paginated);
    }

    // Retrieve a vet by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vet> getVetById(@PathVariable Long id) {
        Optional<Vet> vet = vetService.getVetById(id);
        return vet.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve a vet by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Vet> getVetByName(@PathVariable String name) {
        Optional<Vet> vet = vetService.getVetByName(name);
        return vet.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update vet by ID; returns updated vet
    @PutMapping("/{id}")
    public ResponseEntity<Vet> updateVetById(@PathVariable Long id, @RequestBody Vet updatedVet) {
        try {
            Vet updated = vetService.updateVetById(id, updatedVet);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update vet by name; returns updated vet
    @PutMapping("/name/{name}")
    public ResponseEntity<Vet> updateVetByName(@PathVariable String name, @RequestBody Vet updatedVet) {
        try {
            Vet updated = vetService.updateVetByName(name, updatedVet);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete vet by ID; returns a success message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVetById(@PathVariable Long id) {
        String message = vetService.deleteVetById(id);
        return ResponseEntity.ok(message);
    }

    // Delete vet by name; returns a success message
    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteVetByName(@PathVariable String name) {
        String message = vetService.deleteVetByName(name);
        return ResponseEntity.ok(message);
    }
}

