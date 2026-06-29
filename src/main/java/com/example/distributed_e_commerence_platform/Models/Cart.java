package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.dtos.CartResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends BaseModel {

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @OneToOne
    private User user;


    public CartResponseDto convertToCartResponse( Cart savedCart ){
        CartResponseDto responseDto = new CartResponseDto();
        responseDto.setCartId(savedCart.getId());
        if( savedCart.getUser() != null ){
            responseDto.setUserId(savedCart.getUser().getId());
            responseDto.setUserName(savedCart.getUser().getFirstName());
        }

        return responseDto;
    }



}
