package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.project.custom.entities.User;
import com.example.demo.project.custom.repository.TransactionRepository;
import com.example.demo.project.custom.repository.UserRepository;
import com.example.demo.project.service.TransactionService;

@SpringBootTest
public class TransactionTests {

	@Autowired UserRepository userRepository;
	@Autowired TransactionService transactionService;
	
	@Test
	void contextLoads() { // Buat user dengan saldo awal 0
        User user = new User();
        user.setUsername("testuser");
        user.setBalance(BigDecimal.ZERO);
        user = userRepository.save(user);
        Long userId = user.getId();
        int threadCount = 100;
        int transactionsPerThread = 100;
        BigDecimal amountPerTransaction = BigDecimal.valueOf(1000);

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    for (int j = 0; j < transactionsPerThread; j++) {
                        transactionService.credit(userId, amountPerTransaction);
                    }
                } catch (Exception e) {
					e.printStackTrace();
				} finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        User updatedUser = userRepository.findById(user.getId()).orElseThrow();

        BigDecimal expectedBalance = amountPerTransaction
                .multiply(BigDecimal.valueOf(transactionsPerThread))
                .multiply(BigDecimal.valueOf(threadCount));

        assertEquals(0, updatedUser.getBalance().compareTo(expectedBalance),
                "Saldo akhir user "+updatedUser.getBalance());
    }
}
