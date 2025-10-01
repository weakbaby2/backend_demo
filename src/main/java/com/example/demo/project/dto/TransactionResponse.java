package com.example.demo.project.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TransactionResponse {

	private Long transactionId;
	private BigDecimal newBalance;
	
}
