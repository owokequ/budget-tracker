package com.example.demo.dto;

import java.util.List;

public record UserWithTransactionsRequest(
    UserDTO user,
    List<TransactionsDTO> transactions
) {
} 