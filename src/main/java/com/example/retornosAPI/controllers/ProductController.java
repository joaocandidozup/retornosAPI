package com.example.retornosAPI.controllers;

import com.example.retornosAPI.dtos.Product;
import com.example.retornosAPI.responses.ApiResponse;
import com.example.retornosAPI.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                productCreated,
                null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByName(@RequestParam String name) {
        List<Product> products = service.getProductsByName(name);

        ApiResponse<List<Product>> response = new ApiResponse<>(
                "success",
                "Produtos encontrados com sucesso!",
                products,
                null
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = service.getProductById(id);

        ApiResponse<Product> response = new ApiResponse<>(
                "success",
                "Produto listado com sucesso!",
                product,
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> listProducts = service.getAllProducts();

        ApiResponse<List<Product>> response = new ApiResponse<>(
                "success",
                "Produtos listados com sucesso!!",
                listProducts,
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product updatedProduct) {

        Product productUpdated = service.updateProduct(id, updatedProduct);

        ApiResponse<Product> response = new ApiResponse<>(
                "success",
                "Produto atualizado com sucesso!",
                productUpdated,
                null
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {

        service.deleteProduct(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "success",
                "Produto deletado com sucesso!",
                null,
                null
        );

        return ResponseEntity.ok(response);
    }
}