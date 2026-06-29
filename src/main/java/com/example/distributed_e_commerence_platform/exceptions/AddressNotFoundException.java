package com.example.distributed_e_commerence_platform.exceptions;

public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException( String message ){
        super(message);
    }

}
