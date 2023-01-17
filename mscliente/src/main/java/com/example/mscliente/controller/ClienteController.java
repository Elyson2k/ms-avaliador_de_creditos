package com.example.mscliente.controller;

import com.example.mscliente.model.Cliente;
import com.example.mscliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String teste(){
        return "Ok";
    }

    @PostMapping
    public ResponseEntity<Void> insertCli(@RequestBody Cliente cliente){
        var cpf = clienteService.insertClient(cliente).getCpf();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(cpf).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> pegarCliente(@PathVariable String cpf){
        var cliente = clienteService.findByCliente(cpf);
        return ResponseEntity.ok(cliente.get());
    }
}
