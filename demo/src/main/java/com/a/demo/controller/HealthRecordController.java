package com.a.demo.controller;

import com.a.demo.model.HealthRecord;
import com.a.demo.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/healthrecords")
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    // Create a new health record
    @PostMapping
    public ResponseEntity<HealthRecord> createHealthRecord(@RequestBody HealthRecord healthRecord) {
        HealthRecord created = healthRecordService.createHealthRecord(healthRecord);
        return ResponseEntity.status(201).body(created);
    }

    // Retrieve all health records with pagination and sorting
    @GetMapping
    public ResponseEntity<Page<HealthRecord>> getHealthRecordsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        Page<HealthRecord> paginated = healthRecordService.getHealthRecordsWithPaginationAndSorting(page, size, sortBy, sortOrder);
        return ResponseEntity.ok(paginated);
    }
    
    // Retrieve all health records without pagination
    @GetMapping("/all")
    public ResponseEntity<List<HealthRecord>> getAllHealthRecords() {
        List<HealthRecord> records = healthRecordService.getAllHealthRecords();
        return ResponseEntity.ok(records);
    }

    // Retrieve a health record by ID
    @GetMapping("/{id}")
    public ResponseEntity<HealthRecord> getHealthRecordById(@PathVariable int id) {
        Optional<HealthRecord> record = healthRecordService.getHealthRecordById(id);
        return record.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve health records by pet name (without pagination)
    @GetMapping("/pet/{pet}")
    public ResponseEntity<List<HealthRecord>> getHealthRecordsByPet(@PathVariable String pet) {
        List<HealthRecord> records = healthRecordService.getHealthRecordsByPet(pet);
        return ResponseEntity.ok(records);
    }

    // Update health record by pet name using custom JPQL query and return the updated list
    @PutMapping("/pet/{pet}")
    public ResponseEntity<List<HealthRecord>> updateHealthRecordByPet(@PathVariable String pet, @RequestBody HealthRecord updatedRecord) {
        int updatedRows = healthRecordService.updateHealthRecordByPet(pet, updatedRecord);
        if (updatedRows > 0) {
            List<HealthRecord> updatedRecords = healthRecordService.getHealthRecordsByPet(pet);
            return ResponseEntity.ok(updatedRecords);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update health record by ID using custom JPQL query and return the updated record
    @PutMapping("/{id}")
    public ResponseEntity<HealthRecord> updateHealthRecordById(@PathVariable int id, @RequestBody HealthRecord updatedRecord) {
        int updatedRows = healthRecordService.updateHealthRecordById(id, updatedRecord);
        if (updatedRows > 0) {
            Optional<HealthRecord> updated = healthRecordService.getHealthRecordById(id);
            return updated.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete health record by ID and return a success message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHealthRecordById(@PathVariable int id) {
        healthRecordService.deleteHealthRecordById(id);
        return ResponseEntity.ok("Health record deleted successfully");
    }

    // Delete health records by pet name and return a success message
    @DeleteMapping("/pet/{pet}")
    public ResponseEntity<String> deleteHealthRecordsByPet(@PathVariable String pet) {
        int deletedRows = healthRecordService.deleteHealthRecordsByPet(pet);
        return deletedRows > 0 ? ResponseEntity.ok("Health record(s) deleted successfully")
                               : ResponseEntity.notFound().build();
    }
}
