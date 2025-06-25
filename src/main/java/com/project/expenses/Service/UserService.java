package com.project.expenses.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.expenses.Model.User;
import com.project.expenses.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 🔹 Register user
    public User registerUser(User user) {
        // Optional: check if email already exists
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("❌ Email already registered.");
        }
        return userRepository.save(user);
    }

    // 🔹 Login user by email & password
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("❌ Invalid email or password"));
    }

    // 🔹 Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("❌ User not found with ID: " + id));
    }
}
