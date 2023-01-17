package com.example.mscartoes.service;

import com.example.mscartoes.domain.Cartao;
import com.example.mscartoes.domain.dto.CartaoCliente;
import com.example.mscartoes.domain.dto.CartaoDto;
import com.example.mscartoes.repository.CartaoClienteRepository;
import com.example.mscartoes.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    public Cartao save(CartaoDto cartao){
        var newCartao = fromDto(cartao);
        return repository.save(newCartao);
    }

    public List<Cartao> buscarCartoesRendaMenorIgual(Long renda){
        var newRenda = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(newRenda);
    }

    public Cartao fromDto(CartaoDto cartao){
        Cartao cartaoEntity = new Cartao();
        cartaoEntity.setName(cartao.getName());
        cartaoEntity.setRenda(cartao.getRenda());
        cartaoEntity.setBandeiraCartao(cartao.getBandeira());
        cartaoEntity.setLimiteBasico(cartao.getLimite());
        return cartaoEntity;
    }

}
