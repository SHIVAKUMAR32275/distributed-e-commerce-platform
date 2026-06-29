package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Cart;
import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.dtos.CartRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CartResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.CartNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.UserNotFoundException;
import com.example.distributed_e_commerence_platform.repository.CartRepo;
import com.example.distributed_e_commerence_platform.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService{

    private final UserRepo userRepo ;

    private final CartRepo cartRepo;

    public CartServiceImpl( UserRepo userRepo , CartRepo cartRepo ){
        this.userRepo = userRepo;
        this.cartRepo= cartRepo;
    }

    @Override
    public CartResponseDto createCartForUser(CartRequestDto cartRequestDto) {

        User user = userRepo.findById(cartRequestDto.getUserId())
                .orElseThrow(
                        ()-> new UserNotFoundException(" User not Found with id "+cartRequestDto.getUserId())
                );

        Cart cart = new Cart();
        cart.setUser(user);

        Cart savedCart = cartRepo.save(cart);

        return savedCart.convertToCartResponse(savedCart);
    }

    @Override
    public CartResponseDto getCartDetails(Long cartId) {

        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(
                        () -> new CartNotFoundException(" cart with id "+cartId+ " is not present in repo ")
                );

        return cart.convertToCartResponse(cart);
    }

    @Override
    public List<CartResponseDto> getAllCartDetails() {
        List<CartResponseDto> responseDtoList = new ArrayList<>();

        List<Cart> carts = cartRepo.findAll();

        for( Cart cart : carts ){
            responseDtoList.add(cart.convertToCartResponse(cart));
        }

        return responseDtoList;
    }

    @Override
    public CartResponseDto updateCartDetails(Long cartId, CartRequestDto cartRequestDto) {

        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(
                        () -> new CartNotFoundException(" cart with id "+cartId+ " is not present in repo ")
                );

        User user = userRepo.findById(cartRequestDto.getUserId())
                .orElseThrow(
                        ()-> new UserNotFoundException(" User not Found with id "+cartRequestDto.getUserId())
                );
        cart.setUser(user);

        Cart savedCart = cartRepo.save(cart);

        return savedCart.convertToCartResponse(savedCart);
    }

    @Override
    public void deleteCartDetails(Long cartId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(
                        ()-> new CartNotFoundException(" Cart not found ")
                );

        cartRepo.delete(cart);
    }
}
