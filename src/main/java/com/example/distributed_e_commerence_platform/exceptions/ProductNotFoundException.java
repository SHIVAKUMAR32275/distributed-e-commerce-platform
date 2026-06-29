package com.example.distributed_e_commerence_platform.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message){
        super(message);
    }

}
