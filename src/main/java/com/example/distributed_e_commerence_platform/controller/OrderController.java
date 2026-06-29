package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.OrderRequestDto;
import com.example.distributed_e_commerence_platform.dtos.OrderResponseDto;
import com.example.distributed_e_commerence_platform.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final IOrderService iOrderService;

    public OrderController(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    // CUSTOMER places an order
    @PostMapping("/order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderRequestDto orderRequestDto) {

        OrderResponseDto responseDto =
                iOrderService.createOrder(orderRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.CREATED);
    }

    // CUSTOMER or ADMIN can view an order
    @GetMapping("/order/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<OrderResponseDto> getOrderDetails(
            @PathVariable("id") Long orderId) {

        OrderResponseDto orderResponseDto =
                iOrderService.getOrder(orderId);

        return new ResponseEntity<>(
                orderResponseDto,
                HttpStatus.OK);
    }

    // ADMIN can view all orders
    @GetMapping("/order")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> getAllOrderDetails() {

        List<OrderResponseDto> orderResponseDtoList =
                iOrderService.getAllOrder();

        return new ResponseEntity<>(
                orderResponseDtoList,
                HttpStatus.OK);
    }

    // ADMIN updates order status
    @PutMapping("/order/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderResponseDto> updateOrderDetails(
            @PathVariable("id") Long orderId,
            @RequestBody OrderRequestDto orderRequestDto) {

        OrderResponseDto orderResponseDto =
                iOrderService.updateOrder(
                        orderId,
                        orderRequestDto);

        return new ResponseEntity<>(
                orderResponseDto,
                HttpStatus.OK);
    }

    // ADMIN deletes orders
    @DeleteMapping("/order/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteOrder(
            @PathVariable("id") Long orderId) {

        String str =
                iOrderService.deleteOrder(orderId);

        return new ResponseEntity<>(
                str,
                HttpStatus.OK);
    }
}