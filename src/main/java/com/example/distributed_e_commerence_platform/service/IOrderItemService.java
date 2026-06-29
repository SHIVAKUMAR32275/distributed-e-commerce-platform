package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.OrderItem;
import com.example.distributed_e_commerence_platform.dtos.OrderItemRequestDto;
import com.example.distributed_e_commerence_platform.dtos.OrderItemResponseDto;
import com.example.distributed_e_commerence_platform.dtos.OrderRequestDto;

import java.util.List;

public interface IOrderItemService {

    OrderItemResponseDto createOrderItem(OrderItemRequestDto orderItemRequestDto );

    OrderItemResponseDto getOrderItem( Long orderItemId );

    List<OrderItemResponseDto> getAllOrderItems();

    OrderItemResponseDto updateOrderItem(Long orderItemId , OrderItemRequestDto orderItemRequestDto );

    String deleteOrderItem(Long orderItemId  );
}
