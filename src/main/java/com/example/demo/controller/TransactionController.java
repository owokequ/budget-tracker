package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.dto.TransactionResponseDTO;
import com.example.demo.dto.TransactionsDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserWithTransactionsRequest;
import com.example.demo.entity.UserTransactionEntity;
import com.example.demo.service.TransactionService;

import jakarta.transaction.UserTransaction;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;



// ResponseEntity - можно настраивать код ответа, добавлять хэдеры и тд.


@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionsDTO>> getUser(@PathVariable Long id) {
        log.info("Find user with id={}", id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionService.getUserTransactions(id));
            
    }

    @PostMapping("create")
    public ResponseEntity<String> postMethodName(@RequestBody UserWithTransactionsRequest request 
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createUserTransaction(request.transactions(), request.user()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TransactionResponseDTO> updateUserTransaction(
        @PathVariable Long id,
        @RequestBody TransactionsDTO updateTransaction
    ){
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(transactionService.transactionUpdate(id, updateTransaction));
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteTransaction(
        @PathVariable Long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.transactionDelete(id));
    }

    
    
    
}
