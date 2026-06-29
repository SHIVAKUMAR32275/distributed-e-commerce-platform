package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Inventory;
import com.example.distributed_e_commerence_platform.dtos.InventoryRequestDto;
import com.example.distributed_e_commerence_platform.dtos.InventoryResponseDto;
import com.example.distributed_e_commerence_platform.dtos.ProductRequestDto;

import java.util.List;

public interface IInventoryService {

    InventoryResponseDto createInventory(InventoryRequestDto inventoryRequestDto);

    InventoryResponseDto getInventoryDetails(Long inventoryId);

    List<InventoryResponseDto> getAllInventoryDetails();

    InventoryResponseDto updateInventoryDetails(Long inventoryId,InventoryRequestDto inventoryRequestDto);

    String deleteInventory(Long inventoryId);

}
