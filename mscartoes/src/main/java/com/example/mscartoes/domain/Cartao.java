package com.example.mscartoes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeiraCartao;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao(String name, BandeiraCartao bandeiraCartao, BigDecimal renda, BigDecimal limiteBasico) {
        this.name = name;
        this.bandeiraCartao = bandeiraCartao;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }
}
