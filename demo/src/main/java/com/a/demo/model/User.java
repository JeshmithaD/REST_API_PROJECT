package com.a.demo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Roles stored as a collection of enums
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    
    // For simplicity, store adoptionApplications and reviews as comma-separated strings.
    private String adoptionApplications;
    private String reviews;

    // Default Constructor
    public User() {}

    // Parameterized Constructor
    public User(Long id, String username, String email, String password, Set<Role> roles,
                String adoptionApplications, String reviews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.adoptionApplications = adoptionApplications;
        this.reviews = reviews;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) { 
        this.id = id; 
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) { 
        this.username = username; 
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getAdoptionApplications() {
        return adoptionApplications;
    }
    public void setAdoptionApplications(String adoptionApplications) {
        this.adoptionApplications = adoptionApplications;
    }
    public String getReviews() {
        return reviews;
    }
    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", roles=" + roles +
               ", adoptionApplications='" + adoptionApplications + '\'' +
               ", reviews='" + reviews + '\'' +
               '}';
    }
}
