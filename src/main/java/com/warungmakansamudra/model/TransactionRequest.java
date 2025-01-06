package com.warungmakansamudra.model;

import com.warungmakansamudra.entity.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotNull
    private TransactionType transactionType;

    @NotNull(message = "Receipt number is required")
    @Size(min = 1, max = 255, message = "Receipt number should be between 1 and 255 characters")
    private String receiptNumber;

    @NotNull
    private List<BillDetailRequest> billDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BillDetailRequest {
        @NotNull
        private String productCode;

        @NotNull
        private Integer quantity;
    }

    @NotNull
    private String branchCode;
}