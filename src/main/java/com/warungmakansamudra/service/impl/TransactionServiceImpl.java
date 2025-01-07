package com.warungmakansamudra.service.impl;

import com.warungmakansamudra.entity.*;
import com.warungmakansamudra.exception.BranchNotFoundException;
import com.warungmakansamudra.exception.ProductNotFoundException;
import com.warungmakansamudra.model.TransactionRequest;
import com.warungmakansamudra.model.TransactionResponse;
import com.warungmakansamudra.repository.BillDetailsRepository;
import com.warungmakansamudra.repository.BranchRepository;
import com.warungmakansamudra.repository.ProductRepository;
import com.warungmakansamudra.repository.TransactionRepository;
import com.warungmakansamudra.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        // Create a new Transaction entity
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setReceiptNumber(transactionRequest.getReceiptNumber());
        transaction.setTransactionDate(new Date()); // Set the current date
        transaction.setBillDetails(new ArrayList<>()); // Initialize the billDetails list

        Branch branch = branchRepository.findByBranchCode(transactionRequest.getBranchCode())
                .orElseThrow(() -> new BranchNotFoundException("Branch with code " + transactionRequest.getBranchCode() + " not found."));
        transaction.setBranchCode(branch);

        // Calculate the total price
        double totalPrice = 0.0;
        for (TransactionRequest.BillDetailRequest billDetailRequest : transactionRequest.getBillDetails()) {
            // Fetch the product by product code
            Product product = productRepository.findByProductCode(billDetailRequest.getProductCode())
                    .orElseThrow(() -> new ProductNotFoundException("Product with code " + billDetailRequest.getProductCode() + " not found."));
            double productPrice = product.getPrice().doubleValue();
            totalPrice += productPrice * billDetailRequest.getQuantity();
        }
        transaction.setTotalPrice(totalPrice);

        // Save the transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Save the bill details
        for (TransactionRequest.BillDetailRequest billDetailRequest : transactionRequest.getBillDetails()) {
            BillDetails billDetails = new BillDetails();
            billDetails.setTransaction(savedTransaction);
            // Set the product entity
            Product product = productRepository.findByProductCode(billDetailRequest.getProductCode())
                    .orElseThrow(() -> new ProductNotFoundException("Product with code " + billDetailRequest.getProductCode() + " not found."));
            billDetails.setProductCode(product);
            billDetails.setQuantity(billDetailRequest.getQuantity());
            billDetailsRepository.save(billDetails);
        }

        return convertToTransactionResponse(savedTransaction);
    }

    private TransactionResponse convertToTransactionResponse(Transaction transaction) {
        List<TransactionResponse.BillDetailResponse> billDetails = transaction.getBillDetails().stream()
                .map(bd -> new TransactionResponse.BillDetailResponse(bd.getProductCode().getProductCode(), bd.getQuantity()))
                .collect(Collectors.toList());

        TransactionResponse.BranchResponse branchResponse = new TransactionResponse.BranchResponse(
                transaction.getBranchCode().getId(),
                transaction.getBranchCode().getBranchCode(),
                transaction.getBranchCode().getBranchName(),
                transaction.getBranchCode().getAddress(),
                transaction.getBranchCode().getPhoneNumber()
        );

        return new TransactionResponse(
                transaction.getId(),
                transaction.getTransactionType().name(),
                transaction.getReceiptNumber(),
                transaction.getTransactionDate(),
                transaction.getTotalPrice(),
                billDetails,
                branchResponse
        );
    }

    @Override
    public TransactionResponse getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        return convertToTransactionResponse(transaction);
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(this::convertToTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getTransactionsByReceiptNumber(String receiptNumber) {
        List<Transaction> byReceiptNumber = transactionRepository.findByReceiptNumber(receiptNumber);
        return byReceiptNumber.stream()
                .map(this::convertToTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getTransactionsByDateRange(Date startDate, Date endDate) {
        List<Transaction> byTransactionDateBetween = transactionRepository.findByTransactionDateBetween(startDate, endDate);
        return byTransactionDateBetween.stream()
                .map(this::convertToTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getTransactionsByType(TransactionType transactionType) {
        List<Transaction> byTransactionType = transactionRepository.findByTransactionType(transactionType);
        return byTransactionType.stream()
                .map(this::convertToTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getTransactionsByProductName(String productName) {
        List<Transaction> byProductName = transactionRepository.findByProductName(productName);
        return byProductName.stream()
                .map(this::convertToTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getFilteredTransactions(String receiptNumber, Date startDate, Date endDate, TransactionType transactionType, String productName) {
        List<Transaction> transactions = transactionRepository.findByReceiptNumberAndTransactionDateBetweenAndTransactionTypeAndProductName(receiptNumber, startDate, endDate, transactionType, productName);
        return transactions.stream()
                .map(this::convertToTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateTotalSales(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            return transactionRepository.calculateTotalSalesBetweenDates(startDate, endDate);
        } else {
            return transactionRepository.calculateTotalSales();
        }
    }
}