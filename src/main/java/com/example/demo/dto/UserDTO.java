package com.example.demo.dto;

import java.util.List;

public record UserDTO(
    Long id,
    String name,
    String login,
    String password,
    List<TransactionsDTO> transaction
){}