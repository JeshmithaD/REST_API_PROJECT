package com.a.demo.repository;

import com.a.demo.model.Shelter;
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
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    // Retrieve shelter by name
    @Query("SELECT s FROM Shelter s WHERE s.name = :name")
    Optional<Shelter> findByName(@Param("name") String name);

    // Update shelter details by ID using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE Shelter s SET s.name = :name, s.address = :address, s.phoneNumber = :phoneNumber, s.email = :email WHERE s.id = :id")
    int updateShelterById(@Param("name") String name,
                          @Param("address") String address,
                          @Param("phoneNumber") String phoneNumber,
                          @Param("email") String email,
                          @Param("id") Long id);

    // Update shelter details by name using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE Shelter s SET s.address = :address, s.phoneNumber = :phoneNumber, s.email = :email WHERE s.name = :name")
    int updateShelterByName(@Param("address") String address,
                            @Param("phoneNumber") String phoneNumber,
                            @Param("email") String email,
                            @Param("name") String name);

    // Delete shelter by ID using JPQL (optional, because default deleteById exists)
    @Modifying
    @Transactional
    @Query("DELETE FROM Shelter s WHERE s.id = :id")
    int deleteShelterById(@Param("id") Long id);

    // Delete shelter by name using JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM Shelter s WHERE s.name = :name")
    int deleteShelterByName(@Param("name") String name);

    // For pagination and sorting, the default findAll(Pageable pageable) is used.
    @Override
    Page<Shelter> findAll(Pageable pageable);
}

