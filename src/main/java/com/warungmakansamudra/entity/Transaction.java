package com.warungmakansamudra.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @NotNull(message = "Receipt number is required")
    @Size(min = 1, max = 255, message = "Receipt number should be between 1 and 255 characters")
    @Column(name = "receipt_number", unique = true)
    private String receiptNumber;

    @Column(name = "transaction_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @Column(name = "total_price")
    @NotNull
    private double totalPrice;

    @OneToMany(mappedBy = "transaction")
    private List<BillDetails> billDetails;

    @ManyToOne
    @JoinColumn(name = "branch_code", referencedColumnName = "branch_code")
    private Branch branchCode;
}

