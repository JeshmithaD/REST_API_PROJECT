package com.a.demo.controller;

import com.a.demo.model.User;
import com.a.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(201).body(created);
    }

    // Retrieve all users (non-paginated)
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Retrieve users with pagination and sorting
    @GetMapping("/paginate")
    public ResponseEntity<Page<User>> getUsersWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        Page<User> paginated = userService.getUsersWithPaginationAndSorting(page, size, sortBy, sortOrder);
        return ResponseEntity.ok(paginated);
    }

    // Retrieve a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve a user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update user by ID; returns updated user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUserById(id, updatedUser);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update user by username; returns updated user
    @PutMapping("/username/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUserByUsername(username, updatedUser);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user by ID; returns a success message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        String message = userService.deleteUserById(id);
        return ResponseEntity.ok(message);
    }

    // Delete user by username; returns a success message
    @DeleteMapping("/username/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        String message = userService.deleteUserByUsername(username);
        return ResponseEntity.ok(message);
    }
}
