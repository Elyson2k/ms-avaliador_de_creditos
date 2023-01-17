package com.example.mscartoes.service;

import com.example.mscartoes.domain.dto.CartaoCliente;
import com.example.mscartoes.repository.CartaoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteService {

    private final CartaoClienteRepository cartaoClienteRepository;

    public List<CartaoCliente> cartaoClienteList(String cpf){
        return cartaoClienteRepository.findByCpf(cpf);
    }
}
