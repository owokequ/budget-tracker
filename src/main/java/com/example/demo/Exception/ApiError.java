package com.example.demo.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiError {
 
    private HttpStatus status;
    private String message;
    private LocalDateTime time;
}
