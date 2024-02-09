package com.futurebank.accountService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreationRequest {
    private Long userId;
    private String accountType;

    // Getters and setters
}
