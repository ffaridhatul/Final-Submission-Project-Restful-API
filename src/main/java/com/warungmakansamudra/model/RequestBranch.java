package com.warungmakansamudra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBranch {

    private Long branchId;
    private String branchCode;
    private String branchName;
    private String address;
    private String phoneNumber;
}
