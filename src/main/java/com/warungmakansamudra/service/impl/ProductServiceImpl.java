package com.warungmakansamudra.service.impl;

import com.warungmakansamudra.entity.Product;
import com.warungmakansamudra.entity.Branch;
import com.warungmakansamudra.exception.ProductNotFoundException;
import com.warungmakansamudra.model.CreateProductRequest;
import com.warungmakansamudra.model.UpdateProductRequest;
import com.warungmakansamudra.model.ProductResponse;
import com.warungmakansamudra.repository.ProductRepository;
import com.warungmakansamudra.repository.BranchRepository;
import com.warungmakansamudra.service.ProductService;
import com.warungmakansamudra.validation.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ValidationService validationService;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Transactional
    @Override
    public ProductResponse addProduct(CreateProductRequest request) {
        logger.info("Received request to add product: {}", request);
        validationService.validate(request);

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ProductNotFoundException("Branch with id " + request.getBranchId() + " not found."));

        Product product = new Product();
        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setBranch(branch);

        return toProductResponse(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse getProductById(Long id) {
        logger.info("Received request to get product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));
        return toProductResponse(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getAllProducts() {
        logger.info("Received request to get all products");
        return productRepository.findAll().stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getAllProductsByBranchId(Long branchId) {
        logger.info("Received request to get products by branch id: {}", branchId);
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ProductNotFoundException("Branch with id " + branchId + " not found."));
        return productRepository.findByBranch(branch).stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(Long productId, UpdateProductRequest request) {
        logger.info("Received request to update product: {}", request);
        validationService.validate(request);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found."));

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ProductNotFoundException("Branch with id " + request.getBranchId() + " not found."));

        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setBranch(branch);

        return toProductResponse(productRepository.save(product));
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        logger.info("Received request to delete product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));
        productRepository.delete(product);
    }

    private ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId()) // Set id sebagai Long
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .price(product.getPrice())
                .branchId(product.getBranch().getId()) // Set branchId sebagai Long
                .build();
    }
}