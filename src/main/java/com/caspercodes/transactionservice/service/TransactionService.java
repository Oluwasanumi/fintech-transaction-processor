package com.caspercodes.transactionservice.service;

import com.caspercodes.transactionservice.dto.TransactionRequestDTO;
import com.caspercodes.transactionservice.dto.TransactionResponseDTO;
import com.caspercodes.transactionservice.exception.InvalidCategoryException;
import com.caspercodes.transactionservice.model.Transaction;
import com.caspercodes.transactionservice.model.TransactionCategory;
import com.caspercodes.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Method to process a transaction
    public TransactionResponseDTO processTransaction(TransactionRequestDTO requestDTO) {
        // Validate the category
        TransactionCategory category = TransactionCategory.fromString(requestDTO.category());
        if (category == null) {
            throw new InvalidCategoryException("Invalid category provided");
        }

        // Create transaction
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                requestDTO.amount(),
                requestDTO.transactionDate(),
                category
        );

        // Save the transaction in the repository
        transactionRepository.save(transaction);

        // Return response
        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getCategory().name()
        );
    }

    // New method to get transactions by category
    public List<TransactionResponseDTO> getTransactionsByCategory(String category) {
        // Validate the category
        TransactionCategory transactionCategory = TransactionCategory.fromString(category);
        if (transactionCategory == null) {
            throw new InvalidCategoryException("Invalid category provided");
        }

        // Retrieve all transactions with the given category
        List<Transaction> transactions = transactionRepository.findByCategory(transactionCategory);

        // Convert the transactions to DTOs
        return transactions.stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getTransactionDate(),
                        transaction.getCategory().name()
                ))
                .collect(Collectors.toList());
    }
}
