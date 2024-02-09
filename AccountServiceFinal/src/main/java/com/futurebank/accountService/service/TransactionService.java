package com.futurebank.accountService.service;

import com.futurebank.accountService.model.Transaction;
import com.futurebank.accountService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction, String category) {
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setCategory(category); // Ensure your Transaction model's setCategory accepts a String
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByCategory(String category) {
        // Ensure your TransactionRepository's findByCategory method returns List<Transaction> directly
        return transactionRepository.findByCategory(category);
    }

    public List<Transaction> getTransactionHistoryByAccountId(Long accountId) {
        // Ensure your TransactionRepository's findByAccountId method returns List<Transaction> directly
        return transactionRepository.findByAccountId(accountId);
    }

    // Other service methods...
}
