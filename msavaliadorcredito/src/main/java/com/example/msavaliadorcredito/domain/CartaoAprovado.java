package com.example.msavaliadorcredito.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartaoAprovado {

    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;

    public CartaoAprovado(Cartao cartao) {
        this.cartao = cartao.getName();
        this.bandeira = cartao.getBandeiraCartao();
        this.limiteAprovado = cartao.getLimiteBasico();
    }
}
