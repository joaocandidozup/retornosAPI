package com.example.retornosAPI.services;

import com.example.retornosAPI.dtos.Product;
import com.example.retornosAPI.models.ProductEntity;
import com.example.retornosAPI.repositories.ProductRepository;
import com.example.retornosAPI.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public Product createProduct(Product product) {
        ProductEntity entity = new ProductEntity(null, product.name(), product.description(), product.price(), product.quantityStock(), product.category());
        ProductEntity savedEntity = repository.save(entity);
        return new Product(savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription(), savedEntity.getPrice(), savedEntity.getQuantityStock(), savedEntity.getCategory());
    }

    public Product getProductById(Long id) {

        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o ID: " + id));


        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQuantityStock(),
                entity.getCategory()
        );

    }

    public List<Product> getAllProducts() {
        return repository.findAll().stream()
                .map(entity -> new Product(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getPrice(),
                        entity.getQuantityStock(),
                        entity.getCategory()))
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {

        repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o ID: " + id));
        repository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {

        ProductEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o ID: " + id));

        existingEntity.setName(updatedProduct.name());
        existingEntity.setDescription(updatedProduct.description());
        existingEntity.setPrice(updatedProduct.price());
        existingEntity.setQuantityStock(updatedProduct.quantityStock());
        existingEntity.setCategory(updatedProduct.category());

        ProductEntity savedEntity = repository.save(existingEntity);

        return new Product(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getDescription(),
                savedEntity.getPrice(),
                savedEntity.getQuantityStock(),
                savedEntity.getCategory()
        );
    }

    public List<Product> getProductsByName(String name) {
        Logger logger = LoggerFactory.getLogger(ProductService.class);

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }

        List<ProductEntity> entities = repository.findByNameContainingIgnoreCase(name);
        if (entities.isEmpty()) {
            logger.warn("Nenhum produto encontrado com o nome: {}", name);
        } else {
            logger.info("Produtos encontrados com o nome '{}': {}", name, entities.size());
        }
        return entities.stream()
                .map(entity -> new Product(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getPrice(),
                        entity.getQuantityStock(),
                        entity.getCategory()))
                .collect(Collectors.toList());
    }
}