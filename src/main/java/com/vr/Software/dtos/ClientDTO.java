package com.vr.Software.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
    private Long codigo;
    private String nome;
    private Long limiteCompra;
    private Long diaFechamentoFatura;
}
