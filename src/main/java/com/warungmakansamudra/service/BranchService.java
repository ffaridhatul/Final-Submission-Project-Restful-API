package com.warungmakansamudra.service;

import com.warungmakansamudra.entity.Branch;
import com.warungmakansamudra.payload.request.BranchRequest;
import com.warungmakansamudra.payload.response.BranchResponse;

import java.util.List;

public interface BranchService {
    BranchResponse addBranch(BranchRequest req);
    Branch getBranchById(Long id);
    List<Branch> getAllBranches();
    Branch updateBranch(Branch branch);
    BranchResponse deleteBranchById(Long id);
}