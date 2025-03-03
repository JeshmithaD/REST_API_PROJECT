package com.a.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_records") // Explicit table name for clarity
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String healthCondition;

    @Column(nullable = false)
    private String treatment;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String pet; // Storing pet name for filtering operations

    // Default Constructor
    public HealthRecord() {}

    // Parameterized Constructor
    public HealthRecord(String healthCondition, String treatment, LocalDate date, String pet) {
        this.healthCondition = healthCondition;
        this.treatment = treatment;
        this.date = date;
        this.pet = pet;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }
}
