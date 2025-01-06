package com.warungmakansamudra.controller;

import com.warungmakansamudra.exception.ProductNotFoundException;
import com.warungmakansamudra.model.CreateProductRequest;
import com.warungmakansamudra.model.UpdateProductRequest;
import com.warungmakansamudra.model.ProductResponse;
import com.warungmakansamudra.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    // Endpoint untuk menambahkan produk
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody CreateProductRequest request) {
        ProductResponse response = productService.addProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint untuk mendapatkan produk berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint untuk mendapatkan semua produk
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> response = productService.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint untuk mendapatkan semua produk berdasarkan ID cabang
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ProductResponse>> getAllProductsByBranchId(@PathVariable Long branchId) {
        List<ProductResponse> response = productService.getAllProductsByBranchId(branchId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint untuk memperbarui produk
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductRequest request) {
        try {
            // Set the productId from path variable
            ProductResponse response = productService.updateProduct(id,request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            logger.error("Product not found", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error updating product", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint untuk menghapus produk berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}