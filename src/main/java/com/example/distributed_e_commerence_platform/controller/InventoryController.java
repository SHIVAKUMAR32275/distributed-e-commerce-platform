package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.InventoryRequestDto;
import com.example.distributed_e_commerence_platform.dtos.InventoryResponseDto;
import com.example.distributed_e_commerence_platform.service.IInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class InventoryController {

    private final IInventoryService inventoryService;

    public InventoryController(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventory")
    public ResponseEntity<InventoryResponseDto> createInventory(
            @RequestBody InventoryRequestDto inventoryRequestDto) {

        InventoryResponseDto inventoryResponseDto =
                inventoryService.createInventory(inventoryRequestDto);

        return new ResponseEntity<>(
                inventoryResponseDto,
                HttpStatus.CREATED);
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<InventoryResponseDto> getInventoryDetails(
            @PathVariable("id") Long inventoryId) {

        InventoryResponseDto inventoryResponseDto =
                inventoryService.getInventoryDetails(inventoryId);

        return new ResponseEntity<>(
                inventoryResponseDto,
                HttpStatus.OK);
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryResponseDto>> getAllInventoryDetails() {

        List<InventoryResponseDto> inventoryResponseDtoList =
                inventoryService.getAllInventoryDetails();

        return new ResponseEntity<>(
                inventoryResponseDtoList,
                HttpStatus.OK);
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<InventoryResponseDto> updateInventoryDetails(
            @PathVariable("id") Long inventoryId,
            @RequestBody InventoryRequestDto inventoryRequestDto) {

        InventoryResponseDto inventoryResponseDto =
                inventoryService.updateInventoryDetails(
                        inventoryId,
                        inventoryRequestDto);

        return new ResponseEntity<>(
                inventoryResponseDto,
                HttpStatus.OK);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<String> deleteInventory(
            @PathVariable("id") Long inventoryId) {

        String str =
                inventoryService.deleteInventory(inventoryId);

        return new ResponseEntity<>(
                str,
                HttpStatus.OK);
    }
}