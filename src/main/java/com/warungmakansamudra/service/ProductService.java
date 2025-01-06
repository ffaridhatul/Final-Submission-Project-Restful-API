package com.warungmakansamudra.service;

import com.warungmakansamudra.entity.Product;
import com.warungmakansamudra.model.CreateProductRequest;
import com.warungmakansamudra.model.ProductResponse;
import com.warungmakansamudra.model.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(CreateProductRequest request);
    ProductResponse getProductById(Long id); // Menggunakan Long
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getAllProductsByBranchId(Long branchId); // Menggunakan Long
    ProductResponse updateProduct(UpdateProductRequest request);
    void deleteProductById(Long id); // Menggunakan Long
}