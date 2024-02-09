package com.futurebank.authservice.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.futurebank.authservice.model.User;
import com.futurebank.authservice.repository.UserRepository;
import com.futurebank.authservice.client.AccountClient; // Ensure this import matches your actual AccountClient class

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountClient accountClient; // Dependency for AccountClient

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AccountClient accountClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountClient = accountClient;
    }

    public User registerUser(User user) throws Exception {
        // Check if the user already exists based on username
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("User already exists with username: " + user.getUsername());
        }
        
 
        
        // Encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user
        User savedUser = userRepository.save(user);
        
        // Assuming your AccountClient has a method to create an account for the user
        // Adjust the method name and parameters as necessary
        System.out.println("in account client method near");
        accountClient.createAccountForUser(savedUser,savedUser.getAccountType());
        System.out.println("After account client method near");
        return savedUser;
    }

   
}
