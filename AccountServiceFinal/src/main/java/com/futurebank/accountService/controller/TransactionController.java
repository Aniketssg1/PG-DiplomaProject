package com.futurebank.accountService.controller;

import com.futurebank.accountService.model.MyTransactionCategory;
import com.futurebank.accountService.model.Transaction;
import com.futurebank.accountService.service.TransactionService;

import antlr.collections.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction, @RequestParam String category) {
        Transaction createdTransaction = transactionService.createTransaction(transaction, category);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping
    public ResponseEntity<List> getTransactionsByCategory(@RequestParam String category) {
        List transactions = (List) transactionService.getTransactionsByCategory(category);
        return ResponseEntity.ok(transactions);
    }

    // Add other endpoints as needed...
}
