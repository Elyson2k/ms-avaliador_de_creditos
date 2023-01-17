package com.example.msavaliadorcredito.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class Cartao {

    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private String bandeiraCartao;
    private BigDecimal limiteBasico;

    public Cartao(String name, String bandeiraCartao, BigDecimal limiteBasico){
        this.name = name;
        this.bandeiraCartao = bandeiraCartao;
        this.limiteBasico = limiteBasico;
    }
}
