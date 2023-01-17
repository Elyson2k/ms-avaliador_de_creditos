package com.example.msavaliadorcredito.service;

import com.example.msavaliadorcredito.controller.exceptions.DadosClienteNotFoundException;
import com.example.msavaliadorcredito.controller.exceptions.ErrorComunicacaoMS;
import com.example.msavaliadorcredito.controller.exceptions.ErrorSolicitacaoCartaoException;
import com.example.msavaliadorcredito.domain.*;
import com.example.msavaliadorcredito.infra.clientes.CartoesResourceCliente;
import com.example.msavaliadorcredito.infra.clientes.ClienteResourceCliente;
import com.example.msavaliadorcredito.infra.clientes.mqueue.SolicitarCartaoPublisher;
import feign.FeignException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableFeignClients
public class AvaliadorCreditoService {

    @Autowired
    private ClienteResourceCliente cliente;
    @Autowired
    private CartoesResourceCliente cartoes;
    @Autowired
    private SolicitarCartaoPublisher solicitarCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErrorComunicacaoMS {

        try {
            ResponseEntity<DadosCliente> response = cliente.pegarCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartaoResponse = cartoes.cartaoCliente(cpf);
            return  SituacaoCliente
                    .builder()
                    .cliente(response.getBody())
                    .cartoes(cartaoResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException ex){
            int status = ex.status();
            if(HttpStatus.SC_NOT_FOUND == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ErrorComunicacaoMS(ex.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente retornoAvaliacaoCliente(String cpf, Long renda) throws DadosClienteNotFoundException, ErrorComunicacaoMS {

        try {
            ResponseEntity<DadosCliente> dadosClienteResponseEntity = cliente.pegarCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesRenda = cartoes.listCartoes(renda);

            var cartoesList = cartoesRenda.getBody();

            var listCartoesAprovados = cartoesList.stream().map(cartao -> {

                DadosCliente dadosCliente = dadosClienteResponseEntity.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();

                aprovado.setCartao(cartao.getName());
                aprovado.setBandeira(cartao.getBandeiraCartao());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listCartoesAprovados);

        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if (HttpStatus.SC_NOT_FOUND == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErrorComunicacaoMS(ex.getMessage(), status);
        }
    }

    public Object solicitarEmissaoCartao(DadosSolicitacaoCartao dadosSolicitacaoCartao){
        try{
            solicitarCartaoPublisher.solicitarCartao(dadosSolicitacaoCartao);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (Exception e){
            throw new ErrorSolicitacaoCartaoException(e.getMessage());

        }
    }

}
