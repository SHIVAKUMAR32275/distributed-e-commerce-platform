package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Inventory;
import com.example.distributed_e_commerence_platform.Models.Product;
import com.example.distributed_e_commerence_platform.dtos.InventoryRequestDto;
import com.example.distributed_e_commerence_platform.dtos.InventoryResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.InventoryNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.ProductNotFoundException;
import com.example.distributed_e_commerence_platform.repository.InventoryRepo;
import com.example.distributed_e_commerence_platform.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InventoryServiceImpl implements IInventoryService{

    private InventoryRepo inventoryRepo;

    private ProductRepo productRepo;

    public InventoryServiceImpl( InventoryRepo inventoryRepo , ProductRepo productRepo){
        this.inventoryRepo = inventoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public InventoryResponseDto createInventory(InventoryRequestDto inventoryRequestDto) {

        Product product = productRepo.findById(inventoryRequestDto.getProductId()).
                orElseThrow(()-> new ProductNotFoundException(" Product Not Found !! "));

        Inventory inventory = new Inventory();
        inventory.setAvailableQuantity(inventoryRequestDto.getAvailableQuantity());
        inventory.setReservedQuantity(inventoryRequestDto.getReservedQuantity());

        inventory.setProduct(product);


        Inventory savedInventory = inventoryRepo.save(inventory);

        return inventoryRequestDto.convertToResponse(savedInventory);
    }

    @Override
    public InventoryResponseDto getInventoryDetails(Long inventoryId) {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(()->new InventoryNotFoundException(" Inventory Not Found !! "));

        return inventory.convertToInventoryResponse(inventory);
    }

    @Override
    public List<InventoryResponseDto> getAllInventoryDetails() {
        List<Inventory> inventoryList = inventoryRepo.findAll();

        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();

        for( Inventory inventory : inventoryList ){
            inventoryResponseDtos.add(inventory.convertToInventoryResponse(inventory));
        }
        return inventoryResponseDtos;
    }

    @Override
    public InventoryResponseDto updateInventoryDetails(Long inventoryId, InventoryRequestDto inventoryRequestDto) {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(()->
                        new InventoryNotFoundException
                                (" Inventory not found with" +
                                        " inventory id "+
                                        inventoryId+" is not found "));

        inventory.setAvailableQuantity(inventoryRequestDto.getAvailableQuantity());
        inventory.setReservedQuantity(inventoryRequestDto.getReservedQuantity());

        Inventory savedProduct = inventoryRepo.save(inventory);

        return inventory.convertToInventoryResponse(savedProduct);
    }

    @Override
    public String deleteInventory(Long inventoryId) {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(()->
                        new InventoryNotFoundException(" Inventory not found with id "
                                +inventoryId+
                                "  Enter a valid id "));

        Product product = inventory.getProduct();
        product.setInventory(null);
        productRepo.save(product);

        inventoryRepo.delete(inventory);
        return " Inventory deleted Successfully ..........!!! ";
    }


}
