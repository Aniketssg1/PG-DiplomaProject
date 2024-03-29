package com.futurebank.accountService.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String jwtSecret;
    // You can add more fields here to match other properties prefixed with 'app'
    private long jwtExpirationMs; 
    // Getter and setter for jwtSecret
    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    // Add getters and setters for any additional fields
}
