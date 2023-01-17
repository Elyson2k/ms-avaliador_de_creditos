package com.example.mscartoes.controller;

import com.example.mscartoes.domain.Cartao;
import com.example.mscartoes.domain.dto.CartaoCliente;
import com.example.mscartoes.domain.dto.CartaoDto;
import com.example.mscartoes.service.CartaoClienteService;
import com.example.mscartoes.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cartoes")
public class CartoesController {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private CartaoClienteService cartaoClienteService;

    @PostMapping
    public ResponseEntity<Void> cadastro(@RequestBody CartaoDto request){
        var cartao = cartaoService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{renda}")
    public ResponseEntity<List<Cartao>> listCartoes(@PathVariable Long renda ){
        var cartoes = cartaoService.buscarCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping("/cartoes/{cpf}")
    public ResponseEntity<List<CartaoCliente>> cartaoCliente(@PathVariable String cpf){
        var cartoes = cartaoClienteService.cartaoClienteList(cpf);
        return ResponseEntity.ok(cartoes);
    }
}
