package com.example.msavaliadorcredito.controller.exceptions;

import lombok.Getter;

public class ErrorComunicacaoMS extends Exception{

    @Getter
    private Integer status;

    public ErrorComunicacaoMS(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
