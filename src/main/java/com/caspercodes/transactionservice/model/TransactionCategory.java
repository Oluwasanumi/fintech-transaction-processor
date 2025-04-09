package com.caspercodes.transactionservice.model;

import com.caspercodes.transactionservice.exception.InvalidCategoryException;
import lombok.Getter;

@Getter
public enum TransactionCategory {

    // predefined categories of transactions, transaction will fail if user does not pick at least one of these

    FOOD("Food"),
    TRANSPORT("Transport"),
    UTILITIES("Utilities"),
    SUBSCRIPTIONS("Subscriptions"),
    ENTERTAINMENT("Entertainment"),
    EDUCATION("Education"),
    HEALTHCARE("Healthcare"),
    SHOPPING("Shopping"),
    RENT("Rent"),
    SALARY("Salary"),
    OTHER("Other");

    private final String displayName;

    TransactionCategory(String displayName) {
        this.displayName = displayName;
    }

    public static TransactionCategory fromString(String category) {
        for (TransactionCategory categoryEnum : TransactionCategory.values()) {
            if (categoryEnum.name().equalsIgnoreCase(category)) {
                return categoryEnum;
            }
        }
        throw new InvalidCategoryException("Invalid category provided: " + category);
    }
}
