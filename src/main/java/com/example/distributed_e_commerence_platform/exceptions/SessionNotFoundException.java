package com.example.distributed_e_commerence_platform.exceptions;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException( String message ){
        super(message);
    }

}
