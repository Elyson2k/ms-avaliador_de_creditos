package com.example.mscliente.service;


import com.example.mscliente.model.Cliente;
import com.example.mscliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @GetMapping
    public String teste(){
        return "ok";
    }

    public Cliente insertClient(Cliente cliente){
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findByCliente(String cpf){
        return clienteRepository.findByCpf(cpf);
    }
}
