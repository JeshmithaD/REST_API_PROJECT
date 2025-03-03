package com.a.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;  // e.g., Dog, Cat, Rabbit

    private String breed;
    
    private int age;
    
    private String healthStatus;
    
    private String temperament;
    
    private String adoptionStatus;  // e.g., Available, Adopted, Reserved

    // Reviews stored as plain text or JSON string (for simplicity)
    private String reviews;

    // Default Constructor
    public Pet() {}

    // Parameterized Constructor
    public Pet(String name, String species, String breed, int age, String healthStatus, 
               String temperament, String adoptionStatus, String reviews) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.healthStatus = healthStatus;
        this.temperament = temperament;
        this.adoptionStatus = adoptionStatus;
        this.reviews = reviews;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) { 
        this.age = age; 
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
