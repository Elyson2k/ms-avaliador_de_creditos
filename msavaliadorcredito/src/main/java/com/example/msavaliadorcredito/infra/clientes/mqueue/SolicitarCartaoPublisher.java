package com.example.msavaliadorcredito.infra.clientes.mqueue;

import com.example.msavaliadorcredito.domain.DadosSolicitacaoCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitarCartaoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartao;

    public void solicitarCartao(DadosSolicitacaoCartao dadosSolicitacaoCartao) throws JsonProcessingException {
        var json = convertIntuJson(dadosSolicitacaoCartao);
        rabbitTemplate.convertAndSend(queueEmissaoCartao.getName(), json);
    }

    private String convertIntuJson(DadosSolicitacaoCartao dadosSolicitacaoCartao)throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dadosSolicitacaoCartao);
        return json;
    }
}
