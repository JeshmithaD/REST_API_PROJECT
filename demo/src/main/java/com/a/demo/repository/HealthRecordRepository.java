package com.a.demo.repository;

import com.a.demo.model.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {

    // Find health record by ID using JPQL
    @Query("SELECT h FROM HealthRecord h WHERE h.id = :id")
    Optional<HealthRecord> findHealthRecordById(@Param("id") int id);

    // Find health records by pet name using JPQL
    @Query("SELECT h FROM HealthRecord h WHERE h.pet = :pet")
    List<HealthRecord> findByPet(@Param("pet") String pet);

    // Update health record by ID using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE HealthRecord h SET h.healthCondition = :healthCondition, h.treatment = :treatment, h.date = :date, h.pet = :pet WHERE h.id = :id")
    int updateHealthRecordById(@Param("healthCondition") String healthCondition, 
                               @Param("treatment") String treatment, 
                               @Param("date") LocalDate date,
                               @Param("pet") String pet,
                               @Param("id") int id);

    // Update health record by pet name using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE HealthRecord h SET h.healthCondition = :healthCondition, h.treatment = :treatment, h.date = :date WHERE h.pet = :pet")
    int updateHealthRecordByPet(@Param("healthCondition") String healthCondition, 
                                @Param("treatment") String treatment, 
                                @Param("date") LocalDate date,
                                @Param("pet") String pet);

    // Delete health records by pet name using JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM HealthRecord h WHERE h.pet = :pet")
    int deleteByPet(@Param("pet") String pet);
}
