package com.a.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vetId; // Primary Key

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String clinicInfo;

    @Column(nullable = false)
    private String contactInfo;

    // We store PetId as a string (e.g., could be an identifier or list in CSV format if needed)
    private String petId;

    // Default Constructor
    public Vet() {}

    // Parameterized Constructor
    public Vet(String name, String clinicInfo, String contactInfo, String petId) {
        this.name = name;
        this.clinicInfo = clinicInfo;
        this.contactInfo = contactInfo;
        this.petId = petId;
    }

    // Getters and Setters
    public Long getVetId() {
        return vetId;
    }

    public void setVetId(Long vetId) {
        this.vetId = vetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClinicInfo() {
        return clinicInfo;
    }

    public void setClinicInfo(String clinicInfo) {
        this.clinicInfo = clinicInfo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }
}
