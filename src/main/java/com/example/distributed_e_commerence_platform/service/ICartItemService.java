package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.CartItem;
import com.example.distributed_e_commerence_platform.dtos.CartItemRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CartItemResponseDto;

import java.util.List;

public interface ICartItemService {

    CartItemResponseDto createCartItem(CartItemRequestDto cartItemRequestDto);

    CartItemResponseDto getCartItemDetails(Long cartItemId );

    List<CartItemResponseDto> getAllCartItemDetails();

    CartItemResponseDto updateCartItemDetails(Long cartItemId , CartItemRequestDto cartItemRequestDto );

    void deleteCartItem(Long cartItemId );

}
