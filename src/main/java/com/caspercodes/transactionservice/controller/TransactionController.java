package com.caspercodes.transactionservice.controller;

import com.caspercodes.transactionservice.dto.TransactionRequestDTO;
import com.caspercodes.transactionservice.dto.TransactionResponseDTO;
import com.caspercodes.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    // Inject the service class to interact with the business logic
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint for processing a transaction
    @PostMapping("/process")
    public ResponseEntity<TransactionResponseDTO> processTransaction(@Valid @RequestBody TransactionRequestDTO requestDTO) {
        // Call the service method to process the transaction
        TransactionResponseDTO response = transactionService.processTransaction(requestDTO);
        // Return a successful response with the categorized transaction data
        return ResponseEntity.ok(response);
    }

    // GET endpoint to retrieve all transactions by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByCategory(@PathVariable String category) {
        // Call the service to get transactions by the specified category
        List<TransactionResponseDTO> response = transactionService.getTransactionsByCategory(category);
        return ResponseEntity.ok(response);
    }
}
