package com.a.demo.repository;

import com.a.demo.model.Vet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

    // Retrieve a vet by name using JPQL
    @Query("SELECT v FROM Vet v WHERE v.name = :name")
    Optional<Vet> findByName(@Param("name") String name);

    // Update vet details by ID using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE Vet v SET v.name = :name, v.clinicInfo = :clinicInfo, v.contactInfo = :contactInfo, v.petId = :petId WHERE v.vetId = :id")
    int updateVetById(@Param("name") String name,
                      @Param("clinicInfo") String clinicInfo,
                      @Param("contactInfo") String contactInfo,
                      @Param("petId") String petId,
                      @Param("id") Long id);

    // Update vet details by name using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE Vet v SET v.clinicInfo = :clinicInfo, v.contactInfo = :contactInfo, v.petId = :petId WHERE v.name = :name")
    int updateVetByName(@Param("clinicInfo") String clinicInfo,
                        @Param("contactInfo") String contactInfo,
                        @Param("petId") String petId,
                        @Param("name") String name);

    // Delete vet by ID using JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM Vet v WHERE v.vetId = :id")
    int deleteVetById(@Param("id") Long id);

    // Delete vet by name using JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM Vet v WHERE v.name = :name")
    int deleteVetByName(@Param("name") String name);

    // For pagination and sorting, the default findAll(Pageable pageable) is sufficient.
    @Override
    Page<Vet> findAll(Pageable pageable);
}
