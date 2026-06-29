package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDto {

    private int quantity;

    private Long cartId;

    private Long productId;

}
