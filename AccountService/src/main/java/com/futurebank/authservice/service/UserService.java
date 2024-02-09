package com.futurebank.authservice.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.futurebank.authservice.model.User;
import com.futurebank.authservice.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection for better testability and adherence to SOLID principles
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) throws Exception {
        // Check if the user already exists based on username
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("User already exists with username: " + user.getUsername());
        }
        
        // Generate a unique 12-digit account number
        String accountNumber = generateUniqueAccountNumber();
        user.setAccountNumber(accountNumber);
        
        // Encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Save the user
        return userRepository.save(user);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        boolean isUnique;
        do {
            // Consider a more scalable approach if the user base grows significantly
            accountNumber = RandomStringUtils.randomNumeric(12); // Adjusted to 12 digits as mentioned in your comment
            isUnique = !userRepository.existsByUsername(accountNumber);
        } while (!isUnique);
        return accountNumber;
    }
}
