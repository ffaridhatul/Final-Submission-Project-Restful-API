package com.warungmakansamudra.service;

import com.warungmakansamudra.entity.Branch;

import java.util.List;

public interface BranchService {
    Branch addBranch(Branch branch);
    Branch getBranchById(Long id);
    List<Branch> getAllBranches();
    Branch updateBranch(Branch branch);
    void deleteBranchById(Long id);
}