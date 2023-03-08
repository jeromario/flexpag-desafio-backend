package com.flexpag.paymentscheduler.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String mensagem){
        super(mensagem);
    }
}
