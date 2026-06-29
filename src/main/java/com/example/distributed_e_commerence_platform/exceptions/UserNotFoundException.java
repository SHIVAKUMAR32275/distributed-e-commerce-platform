package com.example.distributed_e_commerence_platform.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException( String message ){
        super(message);
    }

}
