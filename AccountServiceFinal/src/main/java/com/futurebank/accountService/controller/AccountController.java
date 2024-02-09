package com.futurebank.accountService.controller;

import java.util.List;
import com.futurebank.accountService.model.Account;
import com.futurebank.accountService.model.Transaction;
import com.futurebank.accountService.model.TransferRequest;
import com.futurebank.accountService.model.AccountCreationRequest; // Ensure this import is correct
import com.futurebank.accountService.service.AccountService;
import com.futurebank.accountService.service.TransactionService;
import com.futurebank.accountService.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3001")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final TransferService transferService;

    @Autowired
    public AccountController(AccountService accountService, TransactionService transactionService, TransferService transferService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.transferService = transferService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> createAccount(@RequestBody AccountCreationRequest request) {
    	System.out.println("in account create method"+request.getUserId().getClass()+" "+request.getAccountType().getClass());
    	
        Account account = accountService.createAccount(request.getUserId(), request.getAccountType());
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Account creation failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account accountDetails) {
        Account updatedAccount = accountService.updateAccount(accountId, accountDetails);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountId}/summary")   
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {

        Account account = accountService.getAccountById(accountId);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        boolean isDeleted = accountService.deleteAccount(accountId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/transfers")
    public ResponseEntity<?> transferFunds(@RequestBody TransferRequest transferRequest) {
    	System.out.println("data :i m nere");
        boolean success = transferService.transferFunds(
                transferRequest.getFromAccount(),
                transferRequest.getToAccount(),
                transferRequest.getAmount(),
                transferRequest.getCategory()
        );
        if (success) {
            TransferResponse response = new TransferResponse("Transfer successful");
            System.out.println("date :"+ResponseEntity.ok(response).toString());
            return ResponseEntity.ok(response);
        } else {
            TransferResponse response = new TransferResponse("Transfer failed");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.getTransactionHistoryByAccountId(accountId);
        return transactions != null && !transactions.isEmpty() ? ResponseEntity.ok(transactions) : ResponseEntity.notFound().build();
    }

}
