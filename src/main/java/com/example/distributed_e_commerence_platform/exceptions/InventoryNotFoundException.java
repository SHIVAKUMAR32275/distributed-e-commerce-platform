package com.example.distributed_e_commerence_platform.exceptions;

public class InventoryNotFoundException extends RuntimeException{

    public InventoryNotFoundException( String message ){
        super(message);
    }

}
