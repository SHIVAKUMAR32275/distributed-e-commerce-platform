package com.example.distributed_e_commerence_platform.dtos;

import com.example.distributed_e_commerence_platform.Models.Inventory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequestDto {

    private Long productId;

    private int availableQuantity;

    private int reservedQuantity;


    public InventoryResponseDto convertToResponse(Inventory inventory){
        InventoryResponseDto inventoryResponseDto = new InventoryResponseDto();
        inventoryResponseDto.setInventoryId(inventory.getId());
        inventoryResponseDto.setAvailableQuantity(inventory.getAvailableQuantity());
        inventoryResponseDto.setReservedQuantity(inventory.getReservedQuantity());
        inventoryResponseDto.setProductName(inventory.getProduct().getName());
        inventoryResponseDto.setProductId(inventory.getProduct().getId());


        return inventoryResponseDto;
    }

}
