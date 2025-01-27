package com.example.retornosAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Pattern(regexp = "[a-zA-Z0-9\\s]{3,100}", message = "O nome deve ter no mínimo 3 e no máximo 100 caracteres")
    private String name;

    @Pattern(regexp = "[a-zA-Z0-9\\s]{0,500}", message = "A descrição pode ter no máximo 500 caracteres")
    private String description;

    @Positive(message = "O preço tem que ser maior que zero")
    @NotNull(message = "O preço é obrigatório")
    private Double price;

    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Min(value = 0, message = "A quantidade em estoque deve ser um número inteiro maior ou igual a 0")
    private Integer quantityStock;

    @NotBlank(message = "A categoria é obrigatória")
    @Pattern(regexp = "Eletrônicos|Roupas|Alimentos", message = "Categoria inválida! Escolha entre: Eletrônicos, Roupas, Alimentos")
    private String category;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name,String description, Double price,Integer quantityStock,String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityStock = quantityStock;
        this.category = category;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Integer quantityStock) {
        this.quantityStock = quantityStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}