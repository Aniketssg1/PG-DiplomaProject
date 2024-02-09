package com.futurebank.accountService.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.futurebank.accountService.model.MyTransactionCategory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private Long fromAccountId;

    @Column(nullable = false)
    private Long toAccountId;

    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private LocalDateTime transactionDate;
    
        public String getCategory() {
            return category.toString();
        }

        public void setCategory(String category) {
            this.category = category;
        }

}
