package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.TransactionsDTO;
import com.example.demo.entity.UserTransactionEntity;

public interface TransactionRepository extends JpaRepository<UserTransactionEntity, Long>{

    List<TransactionsDTO> findByUserId(Long userId);

}
