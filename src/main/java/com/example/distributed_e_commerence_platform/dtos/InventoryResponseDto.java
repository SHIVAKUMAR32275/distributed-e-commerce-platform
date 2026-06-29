package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InventoryResponseDto {

    private Long productId;

    private int availableQuantity;

    private int reservedQuantity;

    private Long inventoryId;

    private String productName;



}
