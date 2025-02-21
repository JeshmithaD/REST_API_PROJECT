
package com.a.demo.repository;

/*import com.a.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}*/
// Repository

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import jakarta.transaction.Transactional;
import com.a.demo.model.Pet;

@Repository
public interface PetRepo extends JpaRepository<Pet, Integer> {
    // Insert a new pet using a native query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Pet (pet_id, pet_name, pet_type, pet_age, owner_name, owner_address) VALUES (?, ?, ?, ?, ?, ?)", nativeQuery = true)
    void postPet(int petId, String petName, String petType, int petAge, String ownerName, String ownerAddress);

    // Get all pets using JPQL
    @Query("SELECT p FROM Pet p")
    List<Pet> getAllPets();

    // Get pets by a specific age using JPQL
    @Query("SELECT p FROM Pet p WHERE p.petAge = :age")
    List<Pet> getPetsByAge(@Param("age") int age);

    // Get pets by a specific type using JPQL
    @Query("SELECT p FROM Pet p WHERE p.petType = :type")
    List<Pet> getPetsByType(@Param("type") String type);

    // Get pets by owner name using JPQL
    @Query("SELECT p FROM Pet p WHERE p.ownerName = :ownerName")
    List<Pet> getPetsByOwner(@Param("ownerName") String ownerName);
}
