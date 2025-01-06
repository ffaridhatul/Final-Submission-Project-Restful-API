package com.warungmakansamudra.controller;

import com.warungmakansamudra.entity.Transaction;
import com.warungmakansamudra.entity.TransactionType;
import com.warungmakansamudra.model.TransactionRequest;
import com.warungmakansamudra.model.TransactionResponse;
import com.warungmakansamudra.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse transaction = transactionService.createTransaction(transactionRequest);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Long id) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<TransactionResponse> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/receipt/{receiptNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByReceiptNumber(@PathVariable String receiptNumber) {
        List<TransactionResponse> transactions = transactionService.getTransactionsByReceiptNumber(receiptNumber);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<TransactionResponse> transactions = transactionService.getTransactionsByDateRange(startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/type/{transactionType}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByType(@PathVariable TransactionType transactionType) {
        List<TransactionResponse> transactions = transactionService.getTransactionsByType(transactionType);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/product/{productName}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByProductName(@PathVariable String productName) {
        List<TransactionResponse> transactions = transactionService.getTransactionsByProductName(productName);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/filter")
public ResponseEntity<List<TransactionResponse>> getFilteredTransactions(
        @RequestParam(required = false) String receiptNumber,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
        @RequestParam(required = false) TransactionType transactionType,
        @RequestParam(required = false) String productName) {
    List<TransactionResponse> transactions = transactionService.getFilteredTransactions(receiptNumber, startDate, endDate, transactionType, productName);
    return ResponseEntity.ok(transactions);
}
}