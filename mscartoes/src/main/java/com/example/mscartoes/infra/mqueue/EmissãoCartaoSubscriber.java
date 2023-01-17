package com.example.mscartoes.infra.mqueue;

import com.example.mscartoes.domain.Cartao;
import com.example.mscartoes.domain.DadosSolicitacaoCartao;

import com.example.mscartoes.repository.CartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmissãoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final CartaoClienteRepository cartaoClienteRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String paylod){
        System.out.println(paylod);

        try{
            var mappper = new ObjectMapper();
            DadosSolicitacaoCartao dados = mappper.readValue(paylod, DadosSolicitacaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            var newCartao = new CartaoCliente();
            newCartao.setCartao(cartao);
            newCartao.setCpf(dados.getCpf());
            newCartao.setLimite(dados.getLimiteLiberado());

            cartaoClienteRepository.save(newCartao);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }


}
