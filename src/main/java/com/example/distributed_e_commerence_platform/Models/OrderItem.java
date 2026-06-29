package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.dtos.OrderItemResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class OrderItem extends BaseModel{

    private int quantity;

    private BigDecimal priceAtPurchase;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;


    public OrderItemResponseDto convertToOrderItemResponse( OrderItem savedOrderItem ){

        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();

        orderItemResponseDto.setQuantity(savedOrderItem.getQuantity());
        orderItemResponseDto.setPriceAtPurchase(savedOrderItem.getPriceAtPurchase());
        orderItemResponseDto.setOrderItemId(savedOrderItem.getId());

        if( savedOrderItem.getOrder() != null ){
            orderItemResponseDto.setOrderId(savedOrderItem.getId());
        }

        if( savedOrderItem.getProduct() != null ){
            orderItemResponseDto.setProductId(savedOrderItem.getProduct().getId());
            orderItemResponseDto.setProductName(savedOrderItem.getProduct().getName());
        }

        return orderItemResponseDto;

    }


}
