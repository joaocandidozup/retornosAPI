package com.example.retornosAPI.controllers;

import com.example.retornosAPI.dtos.Product;
import com.example.retornosAPI.responses.ApiResponse;
import com.example.retornosAPI.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody Product product) {
        Product productCreated = service.createProduct(product);

        ApiResponse<Product> response = new ApiResponse<>(
                "success",
                "Produto cadastrado com sucesso!",
                productCreated
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        return ResponseEntity.ok(service.getProductById(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Product>> getAllProducts() {
//        return ResponseEntity.ok(service.getAllProducts());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        service.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
}