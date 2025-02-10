package com.vr.Software.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLIENTS")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "Limite compra é obrigatório")
    @Column(name = "limite_compra")
    private Long limiteCompra;

    @NotNull(message = "Dia de fechamento da fatura é obrigatório")
    @Column(name = "dia_fechamento_fatura")
    private Long diaFechamentoFatura;
}
