package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "result_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTransactionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    private Integer amount;
    private String category;
    private String type;
    private String description;
    private LocalDate date;
    
}
