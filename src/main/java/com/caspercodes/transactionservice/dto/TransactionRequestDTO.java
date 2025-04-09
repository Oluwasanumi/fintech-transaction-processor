package com.caspercodes.transactionservice.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDTO(
        @NotNull(message = "Amount cannot be null") BigDecimal amount,
        @NotNull(message = "Transaction Date cannot be null") LocalDateTime transactionDate,
        @NotNull(message = "Category cannot be null") String category
) {}
