package com.example.msavaliadorcredito.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosCliente {

    private Integer id;
    private String name;
    private Integer idade;

}
