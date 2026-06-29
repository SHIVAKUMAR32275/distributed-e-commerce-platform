package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.CartItemRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CartItemResponseDto;
import com.example.distributed_e_commerence_platform.service.ICartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('CUSTOMER')")
public class CartItemController {

    private final ICartItemService iCartItemService;

    public CartItemController(ICartItemService iCartItemService) {
        this.iCartItemService = iCartItemService;
    }

    @PostMapping("/cartItem")
    public ResponseEntity<CartItemResponseDto> createCartItemRecord(
            @RequestBody CartItemRequestDto cartItemRequestDto) {

        CartItemResponseDto responseDto =
                iCartItemService.createCartItem(cartItemRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.CREATED);
    }

    @GetMapping("/cartItem/{id}")
    public ResponseEntity<CartItemResponseDto> getCartItemRecord(
            @PathVariable("id") Long cartItemId) {

        CartItemResponseDto cartItemResponseDto =
                iCartItemService.getCartItemDetails(cartItemId);

        return new ResponseEntity<>(
                cartItemResponseDto,
                HttpStatus.OK);
    }

    @GetMapping("/cartItem")
    public ResponseEntity<List<CartItemResponseDto>> getAllCartItemRecords() {

        List<CartItemResponseDto> cartItemResponseDtos =
                iCartItemService.getAllCartItemDetails();

        return new ResponseEntity<>(
                cartItemResponseDtos,
                HttpStatus.OK);
    }

    @PutMapping("/cartItem/{id}")
    public ResponseEntity<CartItemResponseDto> updateCartItemRecord(
            @PathVariable("id") Long cartItemId,
            @RequestBody CartItemRequestDto cartItemRequestDto) {

        CartItemResponseDto cartItemResponseDto =
                iCartItemService.updateCartItemDetails(
                        cartItemId,
                        cartItemRequestDto);

        return new ResponseEntity<>(
                cartItemResponseDto,
                HttpStatus.OK);
    }

    @DeleteMapping("/cartItem/{id}")
    public ResponseEntity<Void> deleteCartItemRecord(
            @PathVariable("id") Long cartItemId) {

        iCartItemService.deleteCartItem(cartItemId);

        return ResponseEntity.noContent().build();
    }
}