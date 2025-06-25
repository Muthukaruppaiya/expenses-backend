package com.project.expenses.Repository;

import com.project.expenses.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (for login or registration check)
    Optional<User> findByEmail(String email);

    // Optional: This is already inherited from JpaRepository
    // Optional<User> findById(Long id); <-- can be omitted
}
