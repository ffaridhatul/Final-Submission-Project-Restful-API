package com.warungmakansamudra.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "branch_code", unique = true)
    private String branchCode;

    @Column(name = "branch_name")
    private String branchName;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "branch")
    private List<Product> products; // one-to-many relationship with Product entity (assuming you have a Product entity>

    @OneToMany(mappedBy = "branchCode")
    private List<Transaction> transactions;

}
