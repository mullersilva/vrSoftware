package com.vr.Software.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "Descricao do produto é obrigatório")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "Preço do produto é obrigatório")
    @Column(nullable = false)
    private Double preco;
}
