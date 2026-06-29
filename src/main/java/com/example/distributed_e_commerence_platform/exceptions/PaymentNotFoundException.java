package com.example.distributed_e_commerence_platform.exceptions;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException( String message ){
        super(message);
    }
}
