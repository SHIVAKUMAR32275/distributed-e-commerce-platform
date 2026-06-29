package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Long roleId;

    private String roleName;

}
