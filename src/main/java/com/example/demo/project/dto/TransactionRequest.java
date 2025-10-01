package com.example.demo.project.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "DTO Request Bank Account Transaction")
public class TransactionRequest {

	@Schema(description = "ID unik user Id")
	private Long userId;
	
	@NotNull(message = "Amount tidak boleh kosong")
	@Schema(description = "Nominal Amount")
	private BigDecimal amount;
	
}
