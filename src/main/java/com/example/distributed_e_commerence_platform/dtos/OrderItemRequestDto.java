package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class OrderItemRequestDto {

    private int quantity;

    private BigDecimal priceAtPurchase;

    private Long orderId;

    private Long productId;
}
