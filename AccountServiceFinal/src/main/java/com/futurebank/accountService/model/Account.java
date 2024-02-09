package com.futurebank.accountService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Account {
   
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private BigDecimal balance;
    private String accountType;
    private Long userId;
    // Constructors, Getters, and Setters
    public Account() {
    	
        this.balance = BigDecimal.ZERO;
    }
    
}
