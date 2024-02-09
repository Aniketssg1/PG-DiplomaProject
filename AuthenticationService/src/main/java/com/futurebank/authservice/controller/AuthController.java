package com.futurebank.authservice.controller;

import java.util.Map;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futurebank.authservice.model.User;
import com.futurebank.authservice.security.TokenProvider;
import com.futurebank.authservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            logger.error("Registration failed: ", e);
            return ResponseEntity.badRequest().body(null); // Consider creating a specific error response structure
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(authentication );
            String jwt = tokenProvider.generateToken(authentication);
            System.out.println("Sending response with JWT: " + jwt); // Example logging

            return ResponseEntity.ok(new LoginResponse(true, "Login successful", jwt));
        } catch (BadCredentialsException e) {
            logger.error("Login failed: Invalid username or password", e);
            return new ResponseEntity<>(new LoginResponse(false, "Error: Invalid username or password", null), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            logger.error("Internal server error", e);
            return new ResponseEntity<>(new LoginResponse(false, "Internal server error", null), HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/current_user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(Authentication authentication) {
       System.out.println(authentication);
    	if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                // If you want to cast to UserDetailsImpl and get more details, ensure the principal is indeed an instance of UserDetailsImpl
                // and then uncomment the following lines:
                // UserDetailsImpl userDetailsImpl = (UserDetailsImpl) principal;
                // Add additional details from userDetailsImpl as needed

                // Example response with just the username, extend this with more details as needed
                return ResponseEntity.ok(Map.of(
                    "username", username
                    //, "otherDetail", userDetailsImpl.getSomeOtherDetail() // example of adding more details
                ));
            } else {
                // This case handles scenarios where the principal might not be an instance of UserDetails (rare in standard configurations)
                return ResponseEntity.ok(Map.of("username", principal.toString()));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "No authentication found or user is not authenticated"));
    }
}
