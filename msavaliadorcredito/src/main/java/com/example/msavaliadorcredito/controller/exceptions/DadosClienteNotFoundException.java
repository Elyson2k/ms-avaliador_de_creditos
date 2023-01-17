package com.example.msavaliadorcredito.controller.exceptions;

public class DadosClienteNotFoundException extends Exception{

    public DadosClienteNotFoundException(){
        super("Dados do cliente informado não foi encontrado.");
    }
}
