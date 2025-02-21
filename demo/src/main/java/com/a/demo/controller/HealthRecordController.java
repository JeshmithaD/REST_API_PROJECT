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
        return ResponseEntity.status(201).body(healthRecordService.createHealthRecord(healthRecord));
    }

    // Get all health records
    @GetMapping
    public ResponseEntity<List<HealthRecord>> getAllHealthRecords() {
        return ResponseEntity.ok(healthRecordService.getAllHealthRecords());
    }

    // Get health record by ID
    @GetMapping("/{id}")
    public ResponseEntity<HealthRecord> getHealthRecordById(@PathVariable int id) {
        Optional<HealthRecord> healthRecord = healthRecordService.getHealthRecordById(id);
        return healthRecord.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get health records by pet name
    @GetMapping("/pet/{pet}")
    public ResponseEntity<List<HealthRecord>> getHealthRecordsByPet(@PathVariable String pet) {
        return ResponseEntity.ok(healthRecordService.getHealthRecordsByPet(pet));
    }

    // Update health record details
    @PutMapping("/{id}")
    public ResponseEntity<HealthRecord> updateHealthRecord(@PathVariable int id, @RequestBody HealthRecord updatedRecord) {
        return ResponseEntity.ok(healthRecordService.updateHealthRecord(id, updatedRecord));
    }

    // Delete health record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthRecord(@PathVariable int id) {
        healthRecordService.deleteHealthRecord(id);
        return ResponseEntity.noContent().build();
    }

    // Pagination and sorting
    @GetMapping("/paginate")
    public ResponseEntity<Page<HealthRecord>> getHealthRecordsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return ResponseEntity.ok(healthRecordService.getHealthRecordsWithPaginationAndSorting(page, size, sortBy, sortOrder));
    }
}
