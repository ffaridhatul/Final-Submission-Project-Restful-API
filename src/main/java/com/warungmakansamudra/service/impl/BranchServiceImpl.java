package com.warungmakansamudra.service.impl;

import com.warungmakansamudra.entity.Branch;
import com.warungmakansamudra.repository.BranchRepository;
import com.warungmakansamudra.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch addBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public Branch updateBranch(Branch branch) {
        if (branch.getId() == null) {
            throw new IllegalArgumentException("Branch ID must not be null.");
        }

        Branch existingBranch = branchRepository.findById(branch.getId())
                .orElseThrow(() -> new IllegalArgumentException("Branch with ID " + branch.getId() + " not found."));

        existingBranch.setBranchCode(branch.getBranchCode());
        existingBranch.setBranchName(branch.getBranchName());
        existingBranch.setAddress(branch.getAddress());
        existingBranch.setPhoneNumber(branch.getPhoneNumber());

        return branchRepository.save(existingBranch);
    }

    @Override
    public void deleteBranchById(Long id) {
        branchRepository.deleteById(id);
    }
}