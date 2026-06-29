package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {

    private Long cartId;

    private Long userId;

    private String userName;

}
