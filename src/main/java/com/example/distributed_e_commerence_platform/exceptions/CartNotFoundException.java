package com.example.distributed_e_commerence_platform.exceptions;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException( String message ){
        super(message);
    }

}
