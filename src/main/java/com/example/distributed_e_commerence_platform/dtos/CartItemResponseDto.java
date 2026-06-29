package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDto {

    private Long cartItemId;

    private int quantity;

    private Long cartId;

    private Long productId;

    private String productName;


}
