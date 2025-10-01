package com.example.demo.project.custom.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.project.custom.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User>{

	@Modifying
	@Transactional
	@Query("update users set balance = balance + :amount where id = :userId")
	int credit(Long userId, BigDecimal amount);

	@Modifying
	@Transactional
	@Query("update users set balance = balance - :amount WHERE id = :userId and balance >= :amount")
	int debit(Long userId, BigDecimal amount);
	
}
