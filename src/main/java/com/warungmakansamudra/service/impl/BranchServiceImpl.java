package com.warungmakansamudra.service.impl;

import com.warungmakansamudra.entity.Branch;
import com.warungmakansamudra.payload.request.BranchRequest;
import com.warungmakansamudra.payload.response.BranchResponse;
import com.warungmakansamudra.repository.BranchRepository;
import com.warungmakansamudra.service.BranchService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Transactional
    @Override
    public BranchResponse addBranch(BranchRequest request) {
        Branch branch = new Branch();
        branch.setBranchCode(request.getBranchCode());
        branch.setBranchName(request.getBranchName());
        branch.setAddress(request.getAddress());
        branch.setPhoneNumber(request.getPhoneNumber());
        branch.setProducts(request.getProducts());
        branch.setTransactions(request.getTransactions());

        branch = branchRepository.save(branch);

        BranchResponse response = new BranchResponse();
        try {
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Branch added successfully.");
            response.setData(branch);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Branch cannot be null." + e);
        }

        return response;
    }

    @Override
    public Branch getBranchById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Branch with ID " + id + " not found."));
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    @Transactional
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

    @Transactional
    @Override
    public BranchResponse deleteBranchById(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Branch with ID " + id + " not found."));

        // Initialize lazy-loaded collections
        Hibernate.initialize(branch.getProducts());
        Hibernate.initialize(branch.getTransactions());

        branchRepository.deleteById(id);

        return new BranchResponse(HttpStatus.OK.value(), "Branch deleted successfully.", branch);
    }
}