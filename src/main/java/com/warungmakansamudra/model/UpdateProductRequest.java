package com.warungmakansamudra.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {


    @NotBlank
    private String productCode;

    @NotBlank
    private String productName;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private Long branchId;

    // Getters and Setters
}