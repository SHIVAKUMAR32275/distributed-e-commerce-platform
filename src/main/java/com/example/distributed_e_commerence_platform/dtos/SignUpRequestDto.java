package com.example.distributed_e_commerence_platform.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

}
