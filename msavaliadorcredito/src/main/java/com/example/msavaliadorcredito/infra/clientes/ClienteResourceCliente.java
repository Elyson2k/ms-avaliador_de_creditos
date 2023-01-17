package com.example.msavaliadorcredito.infra.clientes;

import com.example.msavaliadorcredito.domain.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( value = "msclientes", path = "/clientes")
public interface ClienteResourceCliente {

    @GetMapping("/{cpf}")
    public ResponseEntity<DadosCliente> pegarCliente(@PathVariable String cpf);


}
