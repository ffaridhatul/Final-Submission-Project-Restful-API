package com.warungmakansamudra.controller;

import com.warungmakansamudra.dto.BranchDto;
import com.warungmakansamudra.entity.Branch;
import com.warungmakansamudra.service.BranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

    @PostMapping
    public ResponseEntity<BranchDto> addBranch(@RequestBody BranchDto branchDto) {
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
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) {
        Branch branch = branchService.getBranchById(id);
        return ResponseEntity.ok(convertToDto(branch));
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        List<BranchDto> branchDto = branches.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(branchDto);
    }

    @PutMapping
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDto branchDto) {
        Branch branch = branchService.updateBranch(convertToEntity(branchDto));
        return ResponseEntity.ok(convertToDto(branch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranchById(@PathVariable Long id) {
        branchService.deleteBranchById(id);
        return ResponseEntity.noContent().build();
    }

    private Branch convertToEntity(BranchDto branchDto) {
        Branch branch = new Branch();
        branch.setId(branchDto.getId());
        branch.setBranchCode(branchDto.getBranchCode());
        branch.setBranchName(branchDto.getBranchName());
        branch.setAddress(branchDto.getAddress());
        branch.setPhoneNumber(branchDto.getPhoneNumber());
        return branch;
    }

    private BranchDto convertToDto(Branch branch) {
        BranchDto branchDto = new BranchDto();
        branchDto.setId(branch.getId());
        branchDto.setBranchCode(branch.getBranchCode());
        branchDto.setBranchName(branch.getBranchName());
        branchDto.setAddress(branch.getAddress());
        branchDto.setPhoneNumber(branch.getPhoneNumber());
        return branchDto;
    }
}