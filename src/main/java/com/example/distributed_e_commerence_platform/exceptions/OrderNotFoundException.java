package com.example.distributed_e_commerence_platform.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException( String message ){
        super(message);
    }
}
