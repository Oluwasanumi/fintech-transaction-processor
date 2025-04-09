package com.caspercodes.transactionservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //a unique identifier is generated
    private UUID id;

    @Column(nullable = false) //amount cannot be null, column for a particular transaction cannot be empty
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    private String description; // optional for narration of transaction details

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Enum is stored as string
    private TransactionCategory category;

    public Transaction(UUID uuid, @NotNull BigDecimal amount, @NotNull LocalDateTime localDateTime, TransactionCategory category) {
    }
}
