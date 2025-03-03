package com.a.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    // Default Constructor
    public Shelter() {}

    // Parameterized Constructor
    public Shelter(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) { 
        this.address = address; 
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
}


