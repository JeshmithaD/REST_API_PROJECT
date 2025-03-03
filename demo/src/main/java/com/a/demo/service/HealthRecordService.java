package com.a.demo.service;

import com.a.demo.model.HealthRecord;
import com.a.demo.repository.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    // Create a new health record using the standard save() method
    public HealthRecord createHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    // Retrieve all health records without pagination
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    // Retrieve health records with pagination and sorting
    public Page<HealthRecord> getHealthRecordsWithPaginationAndSorting(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return healthRecordRepository.findAll(pageable);
    }

    // Retrieve a health record by ID
    public Optional<HealthRecord> getHealthRecordById(int id) {
        return healthRecordRepository.findById(id);
    }

    // Retrieve health records by pet name
    public List<HealthRecord> getHealthRecordsByPet(String pet) {
        return healthRecordRepository.findByPet(pet);
    }

    // Update health record by ID using a custom JPQL query
    @Transactional
    public int updateHealthRecordById(int id, HealthRecord updatedRecord) {
        return healthRecordRepository.updateHealthRecordById(
                updatedRecord.getHealthCondition(),
                updatedRecord.getTreatment(),
                updatedRecord.getDate(),
                updatedRecord.getPet(),
                id
        );
    }

    // Update health record by pet name using a custom JPQL query
    @Transactional
    public int updateHealthRecordByPet(String pet, HealthRecord updatedRecord) {
        return healthRecordRepository.updateHealthRecordByPet(
                updatedRecord.getHealthCondition(),
                updatedRecord.getTreatment(),
                updatedRecord.getDate(),
                pet
        );
    }

    // Delete health record by ID using the default JPA method
    @Transactional
    public void deleteHealthRecordById(int id) {
        healthRecordRepository.deleteById(id);
    }

    // Delete health records by pet name using a custom JPQL query
    @Transactional
    public int deleteHealthRecordsByPet(String pet) {
        return healthRecordRepository.deleteByPet(pet);
    }
}



