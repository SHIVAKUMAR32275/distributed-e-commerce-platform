package com.example.distributed_e_commerence_platform.exceptions;

public class UserNotSignedUpException extends RuntimeException {

    public UserNotSignedUpException( String message ){
        super(message);
    }

}
