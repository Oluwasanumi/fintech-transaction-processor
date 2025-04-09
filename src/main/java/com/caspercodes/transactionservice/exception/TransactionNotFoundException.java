package com.caspercodes.transactionservice.exception;


import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(UUID transactionId) {
        super("Transaction with ID " + transactionId + " not found.");
    }
}
