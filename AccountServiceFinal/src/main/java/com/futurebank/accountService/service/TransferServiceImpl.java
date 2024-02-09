package com.futurebank.accountService.service;

import com.futurebank.accountService.model.Account;
import com.futurebank.accountService.model.MyTransactionCategory;
import com.futurebank.accountService.repository.AccountRepository;
import com.futurebank.accountService.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public boolean transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount,  String  Category) {
        // Ensure the amount is positive
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        // Find both accounts
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        Account toAccount = accountRepository.findById(toAccountId).orElse(null);

        // Check if both accounts exist
        if (fromAccount == null || toAccount == null) {
            return false;
        }

        // Check if the fromAccount has enough balance
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            return false;
        }

        // Perform the transfer
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        // Save the updated account states
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return true;
    }

    @Override
    public boolean transferFunds(Long fromAccountId, Long toAccountId, Double amount2, String Category) {
    	BigDecimal amount=new BigDecimal(amount2); 
    	 // Ensure the amount is positive
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        // Find both accounts
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        Account toAccount = accountRepository.findById(toAccountId).orElse(null);

        // Check if both accounts exist
        if (fromAccount == null || toAccount == null) {
            return false;
        }

        // Check if the fromAccount has enough balance
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            return false;
        }

        // Perform the transfer
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        // Save the updated account states
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return true;

}

	


    
}
