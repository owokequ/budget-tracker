package com.example.demo.dto;

import java.time.LocalDate;

public record TransactionResponseDTO(
    Long id,
    Integer amount,
    String type,
    String category,
    String description,
    LocalDate date,
    Long userId  // только ID, не весь объект
) {}
