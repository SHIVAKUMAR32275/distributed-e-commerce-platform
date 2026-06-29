package com.example.distributed_e_commerence_platform.exceptions;

public class CartItemNotFoundException extends RuntimeException {

    public CartItemNotFoundException(String message ){
        super(message);
    }

}
