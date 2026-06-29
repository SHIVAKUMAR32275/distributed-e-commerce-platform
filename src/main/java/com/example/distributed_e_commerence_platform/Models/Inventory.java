package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.dtos.InventoryResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Inventory extends BaseModel {

    private int availableQuantity;

    private int reservedQuantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;



    public InventoryResponseDto convertToInventoryResponse( Inventory inventory){

        InventoryResponseDto inventoryResponseDto = new InventoryResponseDto();
        inventoryResponseDto.setInventoryId(inventory.getId());
        inventoryResponseDto.setProductName(inventory.getProduct().getName());
        inventoryResponseDto.setReservedQuantity(inventory.getReservedQuantity());
        inventoryResponseDto.setAvailableQuantity(inventory.getAvailableQuantity());
        inventoryResponseDto.setProductId(inventory.getProduct().getId());

        return inventoryResponseDto;
    }


}
