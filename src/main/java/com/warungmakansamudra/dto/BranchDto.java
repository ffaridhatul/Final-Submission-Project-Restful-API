package com.warungmakansamudra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    private Long id;
    private String branchCode;
    private String branchName;
    private String address;
    private String phoneNumber;
}