package com.warungmakansamudra.repository;

import com.warungmakansamudra.entity.Branch;
import com.warungmakansamudra.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBranch(Branch branch);
    Optional<Product> findByProductCode(String productCode);

}