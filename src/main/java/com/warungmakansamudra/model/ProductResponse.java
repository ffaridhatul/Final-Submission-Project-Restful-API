package com.warungmakansamudra.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String productCode;
    private String productName;
    private BigDecimal price;
    private Long branchId;
}