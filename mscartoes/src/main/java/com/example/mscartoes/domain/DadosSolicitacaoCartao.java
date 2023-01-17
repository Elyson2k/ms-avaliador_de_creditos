package com.example.mscartoes.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoCartao {

    private Integer idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
