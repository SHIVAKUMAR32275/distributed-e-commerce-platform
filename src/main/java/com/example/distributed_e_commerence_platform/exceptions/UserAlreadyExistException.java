package com.example.distributed_e_commerence_platform.exceptions;

public class UserAlreadyExistException  extends RuntimeException {
    public UserAlreadyExistException( String message ){
        super(message);
    }
}
