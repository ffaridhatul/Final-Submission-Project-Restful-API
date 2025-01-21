package com.warungmakansamudra.payload.request;

import com.warungmakansamudra.entity.Product;
import com.warungmakansamudra.entity.Transaction;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchRequest {

    private String branchCode;

    @NotBlank
    private String branchName;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    private List<Product> products; // one-to-many relationship with Product entity (assuming you have a Product entity>
    private List<Transaction> transactions;
}
