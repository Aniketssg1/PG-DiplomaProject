package com.futurebank.accountService.controller;

public class TransferResponse {
    private String message;

    public TransferResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
