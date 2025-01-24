package com.example.retornosAPI.controllers;

import com.example.retornosAPI.dtos.Product;
import com.example.retornosAPI.responses.ApiResponse;
import com.example.retornosAPI.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody Product product) {
        logger.info("Recebendo requisição para criar produto: {}", product);

        Product productCreated = service.createProduct(product);
        logger.info("Produto criado com sucesso: {}", productCreated);

        ApiResponse<Product> response = new ApiResponse<>(
                "success",
                "Produto cadastrado com sucesso!",
                productCreated
        );
        logger.info("Retornando resposta para a criação do produto: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByName(@RequestParam String name) {
        // Filtra os produtos pelo nome exato
        List<Product> products = service.getProductsByName(name).stream()
                .filter(product -> product.name().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if (products.isEmpty()) {
            ApiResponse<List<Product>> response = new ApiResponse<>(
                    "error",
                    "Nenhum produto encontrado com o nome : " + name,
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ApiResponse<List<Product>> response = new ApiResponse<>(
                "success",
                "Produtos encontrados com sucesso!",
                products
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = service.getProductById(id);

        ApiResponse<Product> response = new ApiResponse<>(
                "success",
                "Produto listado com sucesso!",
                product
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> listProducts = service.getAllProducts();

        ApiResponse<List<Product>> response = new ApiResponse<>(
                "success",
                "Produtos listados com sucesso!!",
                listProducts
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
                productUpdated
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {

        service.deleteProduct(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "success",
                "Produto deletado com sucesso!",
                null
        );

        return ResponseEntity.ok(response);
    }
}