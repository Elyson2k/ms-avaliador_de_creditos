package com.example.mscartoes.domain.dto;

import com.example.mscartoes.domain.Cartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;
    private BigDecimal limite;

}
