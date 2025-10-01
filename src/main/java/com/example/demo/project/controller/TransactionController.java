package com.example.demo.project.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.project.custom.entities.Transaction;
import com.example.demo.project.dto.TransactionRequest;
import com.example.demo.project.dto.TransactionResponse;
import com.example.demo.project.service.TransactionService;
import com.example.demo.project.utils.JSONResponse;

@RestController
@RequestMapping("/api/transactions/")
public class TransactionController {

	@Autowired TransactionService transactionService;
	
	@PostMapping("/credit")
	public ResponseEntity<JSONResponse<TransactionResponse>> credit(@RequestBody TransactionRequest request) throws Exception {
		try {
			if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
				return new ResponseEntity<JSONResponse<TransactionResponse>>(JSONResponse.<TransactionResponse>builder().status("error").message("Invalid amount").build(), HttpStatus.BAD_REQUEST);
		    }

		    Transaction transaction = transactionService.credit(request.getUserId(), request.getAmount());
		    TransactionResponse responseData = TransactionResponse.builder()
		            .transactionId(transaction.getId())
		            .newBalance(transaction.getUser().getBalance())
		            .build();
			return new ResponseEntity<JSONResponse<TransactionResponse>>(JSONResponse.<TransactionResponse>builder().status("success").data(responseData).build(), HttpStatus.OK);
		
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<JSONResponse<TransactionResponse>>(JSONResponse.<TransactionResponse>builder()
					.status("error")
					.message(e.getMessage()).build(),
					HttpStatus.BAD_REQUEST);
	    }
		
	}

	@PostMapping("/debit")
	public ResponseEntity<JSONResponse<TransactionResponse>> debit(@RequestBody TransactionRequest request) throws Exception {
		try {
			if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
				return new ResponseEntity<JSONResponse<TransactionResponse>>(JSONResponse.<TransactionResponse>builder().status("error").message("Invalid amount").build(), HttpStatus.BAD_REQUEST);
		    }

		    Transaction transaction = transactionService.debit(request.getUserId(), request.getAmount());
		    TransactionResponse responseData = TransactionResponse.builder()
		            .transactionId(transaction.getId())
		            .newBalance(transaction.getUser().getBalance())
		            .build();
			return new ResponseEntity<JSONResponse<TransactionResponse>>(JSONResponse.<TransactionResponse>builder().status("success").data(responseData).build(), HttpStatus.OK);
		
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<JSONResponse<TransactionResponse>>(JSONResponse.<TransactionResponse>builder()
					.status("error")
					.message(e.getMessage()).build(),
					HttpStatus.BAD_REQUEST );
	    }
		
	}
	
}
