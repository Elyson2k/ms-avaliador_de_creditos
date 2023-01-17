package com.example.mscartoes.repository;

import com.example.mscartoes.domain.dto.CartaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoClienteRepository extends JpaRepository<CartaoCliente, Integer> {

    List<CartaoCliente> findByCpf(String cpf);

}
