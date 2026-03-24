package com.example.demo.dto;

import java.time.LocalDate;

public record TransactionsDTO(
    Integer amount,
    String category,
    String type,
    String description,
    LocalDate date
) {

    
} 
