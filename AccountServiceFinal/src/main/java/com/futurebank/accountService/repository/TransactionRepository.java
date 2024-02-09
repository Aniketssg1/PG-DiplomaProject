package com.futurebank.accountService.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.futurebank.accountService.model.Transaction;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.fromAccountId = :accountId OR t.toAccountId = :accountId")
    List<Transaction> findByAccountId(@Param("accountId") Long accountId);

    // Assuming 'category' is a field of type String in your Transaction entity
    // and you want to leverage Spring Data JPA's query creation from method names
    List<Transaction> findByCategory(String category);

    // No need to declare save method here, it's already provided by CrudRepository
}
