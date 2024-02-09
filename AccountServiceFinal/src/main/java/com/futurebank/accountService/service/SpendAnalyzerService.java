package com.futurebank.accountService.service;

import com.futurebank.accountService.model.Transaction;
import com.futurebank.accountService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SpendAnalyzerService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Map<String, BigDecimal> analyzeSpending() {
        // If findAll returns an Iterable, convert it to a List first
        Iterable<Transaction> iterableTransactions = transactionRepository.findAll();
        List<Transaction> transactions = new ArrayList<>();
        iterableTransactions.forEach(transactions::add);

        Map<String, BigDecimal> spendingByCategory = new HashMap<>();
        
        for (Transaction transaction : transactions) {
            // Assuming getCategory returns a String and getAmount returns a BigDecimal
            spendingByCategory.merge(transaction.getCategory(), transaction.getAmount(), BigDecimal::add);
        }

        return spendingByCategory;
    }
}
