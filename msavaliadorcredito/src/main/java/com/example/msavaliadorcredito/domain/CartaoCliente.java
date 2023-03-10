package com.example.msavaliadorcredito.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoCliente {

    private String nome;
    private String bandeira;
    private BigDecimal limite;
}
