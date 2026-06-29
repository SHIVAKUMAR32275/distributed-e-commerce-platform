package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.dtos.CartItemResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem extends BaseModel {

    private int quantity;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;


    public CartItemResponseDto convertToResponse(CartItem cartItem){
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();

        cartItemResponseDto.setCartItemId(cartItem.getId());
        cartItemResponseDto.setQuantity(cartItem.getQuantity());

        if( cartItem.getProduct() != null ){
            cartItemResponseDto.setProductId(cartItem.getProduct().getId());
            cartItemResponseDto.setProductName(cartItem.getProduct().getName());
        }

        if( cartItem.getCart() != null ){
            cartItemResponseDto.setCartId(cartItem.getCart().getId());
        }

        return cartItemResponseDto;
    }


}
