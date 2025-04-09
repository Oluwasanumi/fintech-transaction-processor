package com.caspercodes.transactionservice.service;

import com.caspercodes.transactionservice.dto.TransactionRequestDTO;
import com.caspercodes.transactionservice.dto.TransactionResponseDTO;
import com.caspercodes.transactionservice.exception.TransactionNotFoundException;
import com.caspercodes.transactionservice.model.Transaction;
import com.caspercodes.transactionservice.model.TransactionCategory;
import com.caspercodes.transactionservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository; // Injecting the repository. Service class needs to access database

    //Save a transaction

    public TransactionResponseDTO saveTransaction(TransactionRequestDTO requestDTO) {

        // Making sure the category is valid
        TransactionCategory category;
        try {
            category = TransactionCategory.valueOf(requestDTO.category()); // converts the string category into an enum -> TransactionCategory
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + requestDTO.category());
        }

        //Map DTO to Entity

        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                requestDTO.amount(),
                requestDTO.transactionDate(),
                category);

        // Save transaction to database

        Transaction savedTransaction = transactionRepository.save(transaction);

        // Convert Entity to DTO for response

        return new TransactionResponseDTO(
                savedTransaction.getId(),
                savedTransaction.getAmount(),
                savedTransaction.getTransactionDate(),
                savedTransaction.getCategory().name());
    }

    // Fetch all transactions

    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getTransactionDate(),
                        transaction.getCategory().name())) // converts enum to string
                .collect(Collectors.toList());
    }

    // Fetch transactions by category

    public List<TransactionResponseDTO> getTransactionsByCategory(String categoryName) {
        TransactionCategory category;
        try{
            category = TransactionCategory.valueOf(categoryName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + categoryName);
        }

        return transactionRepository.findByCategory(category).stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getTransactionDate(),
                        transaction.getCategory().name() // converts enum to string
                ))
                .collect(Collectors.toList());
    }

    // Fetch transaction by ID

    public TransactionResponseDTO getTransactionById(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID: " + transactionId));

        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getCategory().name()
        );
    }
}
