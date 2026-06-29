package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Cart;
import com.example.distributed_e_commerence_platform.Models.CartItem;
import com.example.distributed_e_commerence_platform.Models.Product;
import com.example.distributed_e_commerence_platform.dtos.CartItemRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CartItemResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.CartItemNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.CartNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.ProductNotFoundException;
import com.example.distributed_e_commerence_platform.repository.CartItemRepo;
import com.example.distributed_e_commerence_platform.repository.CartRepo;
import com.example.distributed_e_commerence_platform.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartItemServiceImpl implements ICartItemService{

    private final CartItemRepo cartItemRepo;

    private final CartRepo cartRepo;

    private final ProductRepo productRepo;

    public CartItemServiceImpl(CartItemRepo cartItemRepo , CartRepo cartRepo , ProductRepo productRepo ){
        this.cartItemRepo = cartItemRepo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    @Override
    public CartItemResponseDto createCartItem(CartItemRequestDto cartItemRequestDto) {

        Cart cart = cartRepo.findById(cartItemRequestDto.getCartId())
                .orElseThrow(
                        ()-> new CartNotFoundException(" Cart Not found ")
                );

        Product product = productRepo.findById(cartItemRequestDto.getProductId())
                .orElseThrow(
                        ()-> new ProductNotFoundException(" Product Not Found ")
                );

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemRequestDto.getQuantity());

        CartItem savedCartItem = cartItemRepo.save(cartItem);

        return savedCartItem.convertToResponse(savedCartItem);
    }

    @Override
    public CartItemResponseDto getCartItemDetails(Long cartItemId) {
        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(
                        ()-> new CartItemNotFoundException(" Cart Item not Found ")
                );
        return cartItem.convertToResponse(cartItem);
    }

    @Override
    public List<CartItemResponseDto> getAllCartItemDetails() {
        List<CartItemResponseDto> cartItemResponseDtos = new ArrayList<>();

        List<CartItem> cartItems = cartItemRepo.findAll();

        for( CartItem cartItem : cartItems ){
            cartItemResponseDtos.add(cartItem.convertToResponse(cartItem));
        }

        return cartItemResponseDtos;
    }

    @Override
    public CartItemResponseDto updateCartItemDetails(Long cartItemId, CartItemRequestDto cartItemRequestDto) {

        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(
                        ()-> new CartItemNotFoundException(" Cart Item not Found ")
                );

        Cart cart = cartRepo.findById(cartItemRequestDto.getCartId())
                .orElseThrow(
                        ()-> new CartNotFoundException(" Cart Not found ")
                );

        Product product = productRepo.findById(cartItemRequestDto.getProductId())
                .orElseThrow(
                        ()-> new ProductNotFoundException(" Product Not Found ")
                );


        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(cartItemRequestDto.getQuantity());

        CartItem saved = cartItemRepo.save(cartItem);

        return saved.convertToResponse(saved);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(
                        ()-> new CartItemNotFoundException(" Cart Item not Found ")
                );
        cartItemRepo.delete(cartItem);
    }
}
