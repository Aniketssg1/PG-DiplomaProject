package com.futurebank.authservice.model;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;
    private String accountType;
    private String currencyType;
    private String prefix;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date dob;
    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String stateProvince;
    private String postalZipCode;
    private String adhaarNumber;
    private String country;
    private String citizenship;
    private String maritalStatus;
    private String occupation;
    private String employerName;
    private String username; // Ensure this is unique
    private String password;
	public Collection<? extends GrantedAuthority> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
    
    // Constructors, Getters, and Setters
}
