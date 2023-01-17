package com.example.msavaliadorcredito.controller;

import com.example.msavaliadorcredito.controller.exceptions.DadosClienteNotFoundException;
import com.example.msavaliadorcredito.controller.exceptions.ErrorComunicacaoMS;
import com.example.msavaliadorcredito.controller.exceptions.ErrorSolicitacaoCartaoException;
import com.example.msavaliadorcredito.domain.DadosAvaliacao;
import com.example.msavaliadorcredito.domain.DadosSolicitacaoCartao;
import com.example.msavaliadorcredito.domain.SituacaoCliente;
import com.example.msavaliadorcredito.service.AvaliadorCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/avaliacoes-credito")
public class AvaliadorCreditoController {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;


    @GetMapping("/situacao-cliente/{cpf}")
    public ResponseEntity<SituacaoCliente> consultarCliente(@PathVariable String cpf) throws DadosClienteNotFoundException, ErrorComunicacaoMS {
        var response = avaliadorCreditoService.obterSituacaoCliente(cpf);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dadosAvaliacao) throws DadosClienteNotFoundException, ErrorComunicacaoMS {
        var response = avaliadorCreditoService.retornoAvaliacaoCliente(dadosAvaliacao.getCpf(), dadosAvaliacao.getRenda());
        return ResponseEntity.ok(response);
    }

    @PostMapping("solicitacao-cartao")
    public ResponseEntity<?> solicitarCartao(@RequestBody DadosSolicitacaoCartao dadosSolicitacaoCartao){
        try{
            var protocolo = avaliadorCreditoService.solicitarEmissaoCartao(dadosSolicitacaoCartao);
            return ResponseEntity.ok(protocolo);
        } catch (ErrorSolicitacaoCartaoException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
