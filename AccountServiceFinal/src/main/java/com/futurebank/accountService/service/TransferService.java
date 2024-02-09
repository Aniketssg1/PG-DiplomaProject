package com.futurebank.accountService.service;

import java.math.BigDecimal;

import com.futurebank.accountService.model.MyTransactionCategory;

public interface TransferService {


	boolean transferFunds(Long fromAccountId, Long toAccountId, Double amount,String  Category);

	boolean transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount,String Category);


	
}
