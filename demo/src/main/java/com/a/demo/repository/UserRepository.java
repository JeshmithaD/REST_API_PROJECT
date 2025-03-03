package com.a.demo.repository;

import com.a.demo.model.User;
import com.a.demo.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Retrieve a user by username using JPQL
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    // Update user by ID using JPQL. Note: This query will update the existing record.
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :username, u.email = :email, u.password = :password, " +
           "u.adoptionApplications = :adoptionApplications, u.reviews = :reviews " +
           "WHERE u.id = :id")
    int updateUserById(@Param("username") String username,
                       @Param("email") String email,
                       @Param("password") String password,
                       @Param("adoptionApplications") String adoptionApplications,
                       @Param("reviews") String reviews,
                       @Param("id") Long id);

    // Update user by username using JPQL.
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :email, u.password = :password, " +
           "u.adoptionApplications = :adoptionApplications, u.reviews = :reviews " +
           "WHERE u.username = :username")
    int updateUserByUsername(@Param("email") String email,
                             @Param("password") String password,
                             @Param("adoptionApplications") String adoptionApplications,
                             @Param("reviews") String reviews,
                             @Param("username") String username);

    // Delete user by ID using JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    int deleteUserById(@Param("id") Long id);

    // Delete user by username using JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.username = :username")
    int deleteUserByUsername(@Param("username") String username);

    @Override
    Page<User> findAll(Pageable pageable);
}
