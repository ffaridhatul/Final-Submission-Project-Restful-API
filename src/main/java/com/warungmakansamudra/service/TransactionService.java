package com.warungmakansamudra.service;

import com.warungmakansamudra.entity.Transaction;
import com.warungmakansamudra.entity.TransactionType;
import com.warungmakansamudra.model.TransactionRequest;
import com.warungmakansamudra.model.TransactionResponse;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest transactionRequest);
    TransactionResponse getTransactionById(Long id);
    List<TransactionResponse> getAllTransactions();
    List<TransactionResponse> getTransactionsByReceiptNumber(String receiptNumber);
    List<TransactionResponse> getTransactionsByDateRange(Date startDate, Date endDate);
    List<TransactionResponse> getTransactionsByType(TransactionType transactionType);
    List<TransactionResponse> getTransactionsByProductName(String productName);
    List<TransactionResponse> getFilteredTransactions(String receiptNumber, Date startDate, Date endDate, TransactionType transactionType, String productName);
}