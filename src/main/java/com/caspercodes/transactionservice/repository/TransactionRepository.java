package com.caspercodes.transactionservice.repository;

import com.caspercodes.transactionservice.model.Transaction;
import com.caspercodes.transactionservice.model.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByCategory(TransactionCategory category);
}