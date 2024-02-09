package com.futurebank.authservice.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.futurebank.authservice.model.User;
import com.futurebank.authservice.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Autowired PasswordEncoder

    public User registerUser(User user) throws Exception {
        // Check if the user already exists based on username
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("User already exists!");
        }
        
        // Generate a unique 12-digit account number
        String accountNumber = generateUniqueAccountNumber();
        user.setAccountNumber(accountNumber); // Assuming accountNumber is a String
        
        // Encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Save the user
        return userRepository.save(user);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        boolean isUnique;
        do {
            accountNumber = RandomStringUtils.randomNumeric(7);
            isUnique = userRepository.findByAccountNumber(accountNumber).isEmpty(); // Adjust based on your method's implementation
        } while (!isUnique);
        return accountNumber;
    }
}
