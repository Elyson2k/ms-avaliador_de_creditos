package com.example.msavaliadorcredito.infra.clientes;

import com.example.msavaliadorcredito.domain.Cartao;
import com.example.msavaliadorcredito.domain.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient( value = "mscartoes", path = "/cartoes")
public interface CartoesResourceCliente {

    @GetMapping("/cartoes/{cpf}")
    public ResponseEntity<List<CartaoCliente>> cartaoCliente(@PathVariable String cpf);

    @GetMapping("/{renda}")
    public ResponseEntity<List<Cartao>> listCartoes(@PathVariable Long renda );
}
