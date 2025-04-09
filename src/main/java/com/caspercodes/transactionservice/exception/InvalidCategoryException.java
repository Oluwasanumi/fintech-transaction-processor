package com.caspercodes.transactionservice.exception;

public class InvalidCategoryException extends RuntimeException {

    public InvalidCategoryException(String category) {
        super("Invalid transaction category: " + category);
    }
}
