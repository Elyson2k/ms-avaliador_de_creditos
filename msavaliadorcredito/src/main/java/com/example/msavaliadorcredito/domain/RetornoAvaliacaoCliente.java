package com.example.msavaliadorcredito.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {

    List<CartaoAprovado> cartoes = new ArrayList<>();

}
