package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {


    private String houseNo;

    private String streetNo;

    private String city;

    private String state;

    private String pinCode;

    private Long userId;


}
