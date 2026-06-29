package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.CartRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CartResponseDto;
import com.example.distributed_e_commerence_platform.service.ICartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('CUSTOMER')")
public class CartController {

    private final ICartService iCartService;

    public CartController(ICartService iCartService) {
        this.iCartService = iCartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<CartResponseDto> createCartRecord(
            @RequestBody CartRequestDto cartRequestDto) {

        CartResponseDto cartResponseDto =
                iCartService.createCartForUser(cartRequestDto);

        return new ResponseEntity<>(cartResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartResponseDto> getCartRecord(
            @PathVariable("id") Long cartId) {

        CartResponseDto response =
                iCartService.getCartDetails(cartId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartResponseDto>> getAllCartRecords() {

        List<CartResponseDto> responseDtoList =
                iCartService.getAllCartDetails();

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<CartResponseDto> updateCartRecord(
            @PathVariable("id") Long cartId,
            @RequestBody CartRequestDto cartRequestDto) {

        CartResponseDto cartResponseDto =
                iCartService.updateCartDetails(cartId, cartRequestDto);

        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Void> deleteCartRecord(
            @PathVariable("id") Long cartId) {

        iCartService.deleteCartDetails(cartId);

        return ResponseEntity.noContent().build();
    }
}