package com.warungmakansamudra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBranchRequest {

    private String branchCode;

    private String branchName;

    private String address;

    private String phoneNumber;
}
