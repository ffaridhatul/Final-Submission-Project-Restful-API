// TransactionResponse.java
package com.warungmakansamudra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long id;
    private String transactionType;
    private String receiptNumber;
    private Date transactionDate;
    private double totalPrice;
    private List<BillDetailResponse> billDetails;
    private BranchResponse branchCode;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BillDetailResponse {
        private String productCode;
        private Integer quantity;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BranchResponse {
        private Long id;
        private String branchCode;
        private String branchName;
        private String address;
        private String phoneNumber;
    }
}