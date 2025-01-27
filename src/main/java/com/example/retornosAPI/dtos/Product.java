package com.example.retornosAPI.dtos;

import jakarta.validation.constraints.*;

public record Product(
        Long id,
        @NotBlank(message = "O nome é obrigatorio")
        @Pattern(regexp = "[a-zA-Z0-9\\s]{3,100}", message = "O nome deve ter no mínimo 3 e no máximo 100 caracteres")
        String name,
        @Pattern(regexp = "[a-zA-Z0-9\\s]{0,500}", message = "A descrição pode ter no máximo 500 caracteres")
        String description,
        @Positive(message = "O preço tem que ser maior que zero")
        @NotNull(message = "O preço é obrigatorio")
        Double price,
        @NotNull(message = "A quantidade em estoque é obrigatoria")
        @Min(value = 0, message = "A quantidade em estoque deve ser um número inteiro maior ou igual a 0")
        Integer quantityStock,
        @NotBlank(message = "A categoria é obrigatoria")
        @Pattern(regexp = "Eletrônicos|Roupas|Alimentos", message = "Categoria invalida! escolha entre: Eletrônicos, Roupas, Alimentos")
        String category
) {

}