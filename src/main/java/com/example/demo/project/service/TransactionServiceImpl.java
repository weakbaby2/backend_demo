package com.example.demo.project.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.project.custom.entities.Transaction;
import com.example.demo.project.custom.entities.User;
import com.example.demo.project.custom.repository.TransactionRepository;
import com.example.demo.project.custom.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired UserRepository userRepository;
	@Autowired TransactionRepository transactionRepository;

	@Autowired MessageSource messageSource;
	
	@Transactional
    public Transaction credit(Long userId, BigDecimal amount) {
		
		int updateAmount = userRepository.credit(userId, amount);
	    if (updateAmount == 0) {
	        throw new IllegalArgumentException("User not found");
	    }
	    
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));		

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setType("credit");
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction debit(Long userId, BigDecimal amount) {
    	
    	int updateAmount = userRepository.debit(userId, amount);
        if (updateAmount == 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Transaction transaction = new Transaction();

        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setType("debit");
        return transactionRepository.save(transaction);
    }

}
