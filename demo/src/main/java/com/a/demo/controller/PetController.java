package com.a.demo.controller;

import com.a.demo.model.Pet;
import com.a.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    
    @Autowired
    private PetService petService;

  
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet createdPet = petService.createPet(pet);
        return ResponseEntity.status(201).body(createdPet);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.getPetById(id);
        return pet.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    @GetMapping("/name/{name}")
    public ResponseEntity<Pet> getPetByName(@PathVariable String name) {
        Optional<Pet> pet = petService.getPetByName(name);
        return pet.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/sortBy/{field}")
    public ResponseEntity<List<Pet>> sortPets(@PathVariable String field) {
        List<Pet> pets = petService.sortPets(field);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<Pet>> getPaginatedPets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Pet> paginated = petService.getPaginatedPets(page, size);
        return ResponseEntity.ok(paginated);
    }


    @GetMapping("/paginate/sort")
    public ResponseEntity<Page<Pet>> getPaginatedSortedPets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String field) {
        Page<Pet> paginatedSorted = petService.getPaginatedSortedPets(page, size, field);
        return ResponseEntity.ok(paginatedSorted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePetById(@PathVariable Long id, @RequestBody Pet updatedPet) {
        int updatedRows = petService.updatePetById(id, updatedPet);
        if (updatedRows > 0) {
            Optional<Pet> updated = petService.getPetById(id);
            return updated.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/name/{name}")
    public ResponseEntity<Pet> updatePetByName(@PathVariable String name, @RequestBody Pet updatedPet) {
        int updatedRows = petService.updatePetByName(name, updatedPet);
        if (updatedRows > 0) {
            Optional<Pet> updated = petService.getPetByName(name);
            return updated.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id) {
        boolean deleted = petService.deletePet(id);
        return deleted ? ResponseEntity.ok("Pet deleted successfully") 
                       : ResponseEntity.status(404).body("Pet not found");
    }


    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deletePetByName(@PathVariable String name) {
        int deletedRows = petService.deletePetByName(name);
        return deletedRows > 0 ? ResponseEntity.ok("Pet deleted successfully") 
                               : ResponseEntity.status(404).body("Pet not found");
    }
}
