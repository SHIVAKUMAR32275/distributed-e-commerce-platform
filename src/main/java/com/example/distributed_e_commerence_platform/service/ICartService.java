package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.CartRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CartResponseDto;

import java.util.List;

public interface ICartService {

    CartResponseDto createCartForUser(CartRequestDto cartRequestDto);

    CartResponseDto getCartDetails(Long cartId );

    List<CartResponseDto> getAllCartDetails();

    CartResponseDto updateCartDetails(Long cartId,CartRequestDto cartRequestDto );

    void deleteCartDetails(Long cartId );

}
