package com.caspercodes.transactionservice.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// For API input

public record TransactionRequestDTO(@NotNull BigDecimal amount,
                                    @NotNull LocalDateTime transactionDate,
                                    @NotNull String category) {
}
