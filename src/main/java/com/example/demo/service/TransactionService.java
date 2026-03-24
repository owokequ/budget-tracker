package com.example.demo.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.dto.TransactionResponseDTO;
import com.example.demo.dto.TransactionsDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserTransactionEntity;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;


@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    };

    public List<TransactionsDTO> getUserTransactions(Long id){

        if(!userRepository.existsById(id)){
            throw new NotFoundException("Пользователь не найден");
        }

        return transactionRepository.findByUserId(id).stream().map(el -> new TransactionsDTO(
            el.amount(),
            el.type(),
            el.category(),
            el.description(),
            el.date()
        )).toList();   
    }

    public String createUserTransaction(List<TransactionsDTO> trasaction, UserDTO getUser){

       if(transactionRepository.findByUserId(getUser.id()) != null){
            throw new RuntimeErrorException(new Error(), "Пользователь уже существует");
        }
        UserEntity user = new UserEntity(
            null,
            getUser.login(),
            getUser.password(),
            getUser.name(),
            null                
        );
        userRepository.save(user);
        List<UserTransactionEntity> entities = trasaction.stream()
        .map(dto -> {
            UserTransactionEntity transact = new UserTransactionEntity(
                null,
                user,
                dto.amount(),
                dto.category(),
                dto.type(),
                dto.description(),
                dto.date()
            );
            return transact;
        })
        .toList();
        transactionRepository.saveAll(entities);
        return new String("good");
    }

    public TransactionResponseDTO transactionUpdate(Long transaction_id, TransactionsDTO transaction){
        UserTransactionEntity newTransaction = transactionRepository.findById(transaction_id)
        .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + transaction_id));

        newTransaction.setAmount(transaction.amount());
        newTransaction.setCategory(transaction.category());
        newTransaction.setDate(transaction.date());
        newTransaction.setDescription(transaction.description());
        newTransaction.setType(transaction.type());

        transactionRepository.save(newTransaction);
        return new TransactionResponseDTO(
            newTransaction.getId(),
            newTransaction.getAmount(),
            newTransaction.getType(),
            newTransaction.getCategory(),
            newTransaction.getDescription(),
            newTransaction.getDate(),
            newTransaction.getUser().getId()
        );
    }


    public String transactionDelete(Long id){
        transactionRepository.deleteById(id);
        return new String("Transaction deleted");
    }


    // public UserDTO ResponseBD(UserDTO user){
    //     UserDTO result = transactionRepository.createUser(user);
    //     return result;
    // }

    // public Map<Long, UserDTO> all(){
    //     return transactionRepository.allUserBD();
    // }
}
