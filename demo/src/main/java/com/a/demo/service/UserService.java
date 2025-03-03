package com.a.demo.service;

import com.a.demo.model.User;
import com.a.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Retrieve all users (non-paginated)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve users with pagination and sorting
    public Page<User> getUsersWithPaginationAndSorting(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }

    // Retrieve a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Retrieve a user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Update user by ID using custom JPQL update query.
    // This updates the existing record instead of creating a new one.
    @Transactional
    public User updateUserById(Long id, User updatedUser) {
        int updatedRows = userRepository.updateUserById(
                updatedUser.getUsername(),
                updatedUser.getEmail(),
                updatedUser.getPassword(),
                updatedUser.getAdoptionApplications(),
                updatedUser.getReviews(),
                id
        );
        if (updatedRows > 0) {
            return userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found after update with ID: " + id));
        }
        throw new RuntimeException("User not found with ID: " + id);
    }

    // Update user by username using custom JPQL update query.
    @Transactional
    public User updateUserByUsername(String username, User updatedUser) {
        int updatedRows = userRepository.updateUserByUsername(
                updatedUser.getEmail(),
                updatedUser.getPassword(),
                updatedUser.getAdoptionApplications(),
                updatedUser.getReviews(),
                username
        );
        if (updatedRows > 0) {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found after update with username: " + username));
        }
        throw new RuntimeException("User not found with username: " + username);
    }

    // Delete user by ID using custom JPQL query
    @Transactional
    public String deleteUserById(Long id) {
        int deletedRows = userRepository.deleteUserById(id);
        return deletedRows > 0 ? "User deleted successfully" : "User not found";
    }

    // Delete user by username using custom JPQL query
    @Transactional
    public String deleteUserByUsername(String username) {
        int deletedRows = userRepository.deleteUserByUsername(username);
        return deletedRows > 0 ? "User deleted successfully" : "User not found";
    }
}
