package com.futurebank.authservice.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {
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
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Note: Implementations for other methods from UserDetails if needed

    // Constructors, Getters, and Setters for the rest of the fields
}
