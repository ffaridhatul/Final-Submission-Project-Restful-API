package com.warungmakansamudra.repository;

import com.warungmakansamudra.entity.Transaction;
import com.warungmakansamudra.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByReceiptNumber(String receiptNumber);
    List<Transaction> findByTransactionDateBetween(Date startDate, Date endDate);
    List<Transaction> findByTransactionType(TransactionType transactionType);

    // Custom query to find transactions by product name
    @Query("SELECT t FROM Transaction t JOIN t.billDetails bd JOIN bd.productCode p WHERE p.productName = :productName")
    List<Transaction> findByProductName(@Param("productName") String productName);

    // Custom query to find transactions with multiple filters
    @Query("SELECT t FROM Transaction t JOIN t.billDetails bd JOIN bd.productCode p WHERE " +
            "(:receiptNumber IS NULL OR t.receiptNumber = :receiptNumber) AND " +
            "(:startDate IS NULL OR t.transactionDate >= :startDate) AND " +
            "(:endDate IS NULL OR t.transactionDate <= :endDate) AND " +
            "(:transactionType IS NULL OR t.transactionType = :transactionType) AND " +
            "(:productName IS NULL OR p.productName = :productName)")
    List<Transaction> findByReceiptNumberAndTransactionDateBetweenAndTransactionTypeAndProductName(
            @Param("receiptNumber") String receiptNumber,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("transactionType") TransactionType transactionType,
            @Param("productName") String productName);


    @Query("SELECT SUM(t.totalPrice) FROM Transaction t")
    double calculateTotalSales();

    @Query("SELECT SUM(t.totalPrice) FROM Transaction t WHERE t.transactionDate BETWEEN :startDate AND :endDate")
    double calculateTotalSalesBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}