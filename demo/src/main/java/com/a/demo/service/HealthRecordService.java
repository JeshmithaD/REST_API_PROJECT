package com.a.demo.service;

import com.a.demo.model.HealthRecord;
import com.a.demo.repository.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    public HealthRecord createHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    public Optional<HealthRecord> getHealthRecordById(int id) {
        return healthRecordRepository.findById(id);
    }

    public List<HealthRecord> getHealthRecordsByPet(String pet) {
        return healthRecordRepository.findByPet(pet);
    }

    public HealthRecord updateHealthRecord(int id, HealthRecord updatedRecord) {
        return healthRecordRepository.findById(id)
                .map(record -> {
                    record.setHealthCondition(updatedRecord.getHealthCondition());
                    record.setTreatment(updatedRecord.getTreatment());
                    record.setDate(updatedRecord.getDate());
                    record.setPet(updatedRecord.getPet());
                    return healthRecordRepository.save(record);
                })
                .orElseThrow(() -> new RuntimeException("Health Record not found with ID: " + id));
    }

    public void deleteHealthRecord(int id) {
        healthRecordRepository.deleteById(id);
    }

    public Page<HealthRecord> getHealthRecordsWithPaginationAndSorting(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return healthRecordRepository.findAll(pageable);
    }
}
