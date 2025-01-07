package com.warungmakansamudra.controller;

import com.warungmakansamudra.entity.Branch;
import com.warungmakansamudra.model.RequestBranch;
import com.warungmakansamudra.service.BranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

    @PostMapping
    public ResponseEntity<RequestBranch> addBranch(@RequestBody RequestBranch branchDto) {
        logger.info("Received request to add branch: {}", branchDto);
        try {
            Branch branch = branchService.addBranch(convertToEntity(branchDto));
            return ResponseEntity.ok(convertToDto(branch));
        } catch (Exception e) {
            logger.error("Error adding branch", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestBranch> getBranchById(@PathVariable Long id) {
        Branch branch = branchService.getBranchById(id);
        return ResponseEntity.ok(convertToDto(branch));
    }

    @GetMapping
    public ResponseEntity<List<RequestBranch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        List<RequestBranch> branchDto = branches.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(branchDto);
    }

    @PutMapping
    public ResponseEntity<RequestBranch> updateBranch(@RequestBody RequestBranch requestUpdateBranch) {
        Branch branch = convertToEntity(requestUpdateBranch);
        Branch updatedBranch = branchService.updateBranch(branch);
        return ResponseEntity.ok(convertToDto(updatedBranch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranchById(@PathVariable Long id) {
        branchService.deleteBranchById(id);
        return ResponseEntity.noContent().build();
    }

    private Branch convertToEntity(RequestBranch branchDto) {
        Branch branch = new Branch();
        branch.setId(branchDto.getBranchId());
        branch.setBranchCode(branchDto.getBranchCode());
        branch.setBranchName(branchDto.getBranchName());
        branch.setAddress(branchDto.getAddress());
        branch.setPhoneNumber(branchDto.getPhoneNumber());
        return branch;
    }

    private RequestBranch convertToDto(Branch branch) {
        RequestBranch branchDto = new RequestBranch();
        branchDto.setBranchId(branch.getId());
        branchDto.setBranchCode(branch.getBranchCode());
        branchDto.setBranchName(branch.getBranchName());
        branchDto.setAddress(branch.getAddress());
        branchDto.setPhoneNumber(branch.getPhoneNumber());
        return branchDto;
    }
}