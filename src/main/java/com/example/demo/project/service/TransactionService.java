package com.example.demo.project.service;

import java.math.BigDecimal;

import com.example.demo.project.custom.entities.Transaction;

public interface TransactionService {
	
	Transaction credit(Long userId, BigDecimal amount) throws Exception;
	Transaction debit(Long userId, BigDecimal amount) throws Exception;

}
