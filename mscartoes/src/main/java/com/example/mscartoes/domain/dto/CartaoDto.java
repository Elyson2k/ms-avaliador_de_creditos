package com.example.mscartoes.domain.dto;

import com.example.mscartoes.domain.Cartao;
import com.example.mscartoes.domain.enums.BandeiraCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDto {

    private String name;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public CartaoDto(Cartao cartao){
        this.name = cartao.getName();
        this.bandeira = cartao.getBandeiraCartao();
        this.renda = cartao.getRenda();
        this.limite = cartao.getLimiteBasico();
    }
}
