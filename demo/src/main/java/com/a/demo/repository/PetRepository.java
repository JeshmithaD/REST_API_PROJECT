package com.a.demo.repository;

import com.a.demo.model.Pet;
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
public interface PetRepository extends JpaRepository<Pet, Long> {

    // Retrieve a pet by name
    @Query("SELECT p FROM Pet p WHERE p.name = :name")
    Optional<Pet> findByName(@Param("name") String name);

    // Delete a pet by name using JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE p.name = :name")
    int deleteByName(@Param("name") String name);

    // Update pet details by ID using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE Pet p SET p.name = :newName, p.species = :species, p.breed = :breed, p.age = :age, " +
           "p.healthStatus = :healthStatus, p.temperament = :temperament, p.adoptionStatus = :adoptionStatus, " +
           "p.reviews = :reviews WHERE p.id = :id")
    int updatePetById(@Param("newName") String newName,
                      @Param("species") String species,
                      @Param("breed") String breed,
                      @Param("age") int age,
                      @Param("healthStatus") String healthStatus,
                      @Param("temperament") String temperament,
                      @Param("adoptionStatus") String adoptionStatus,
                      @Param("reviews") String reviews,
                      @Param("id") Long id);

    // Update pet details by name using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE Pet p SET p.species = :species, p.breed = :breed, p.age = :age, " +
           "p.healthStatus = :healthStatus, p.temperament = :temperament, p.adoptionStatus = :adoptionStatus, " +
           "p.reviews = :reviews WHERE p.name = :name")
    int updatePetByName(@Param("species") String species,
                        @Param("breed") String breed,
                        @Param("age") int age,
                        @Param("healthStatus") String healthStatus,
                        @Param("temperament") String temperament,
                        @Param("adoptionStatus") String adoptionStatus,
                        @Param("reviews") String reviews,
                        @Param("name") String name);

    // For pagination and sorting, the default findAll(Pageable pageable) is sufficient.
    @Override
    Page<Pet> findAll(Pageable pageable);
}

