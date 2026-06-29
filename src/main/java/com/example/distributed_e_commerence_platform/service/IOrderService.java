package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.OrderRequestDto;
import com.example.distributed_e_commerence_platform.dtos.OrderResponseDto;

import java.util.List;

public interface IOrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrder(Long orderId);

    List<OrderResponseDto> getAllOrder();

    OrderResponseDto updateOrder(Long orderId , OrderRequestDto orderRequestDto);

    String deleteOrder(Long orderId);

}
