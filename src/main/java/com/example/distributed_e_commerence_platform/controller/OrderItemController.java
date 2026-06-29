package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.OrderItemRequestDto;
import com.example.distributed_e_commerence_platform.dtos.OrderItemResponseDto;
import com.example.distributed_e_commerence_platform.service.IOrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class OrderItemController {

    private final IOrderItemService iOrderItemService;

    public OrderItemController(
            IOrderItemService iOrderItemService) {

        this.iOrderItemService =
                iOrderItemService;
    }

    @PostMapping("/orderItem")
    public ResponseEntity<OrderItemResponseDto>
    createOrderItem(
            @RequestBody
            OrderItemRequestDto orderItemRequestDto) {

        OrderItemResponseDto orderItemResponseDto =
                iOrderItemService
                        .createOrderItem(
                                orderItemRequestDto);

        return new ResponseEntity<>(
                orderItemResponseDto,
                HttpStatus.CREATED);
    }

    @GetMapping("/orderItem/{id}")
    public ResponseEntity<OrderItemResponseDto>
    getOrderItem(
            @PathVariable("id")
            Long orderItemId) {

        OrderItemResponseDto orderItemResponseDto =
                iOrderItemService
                        .getOrderItem(orderItemId);

        return new ResponseEntity<>(
                orderItemResponseDto,
                HttpStatus.OK);
    }

    @GetMapping("/orderItem")
    public ResponseEntity<List<OrderItemResponseDto>>
    getAllOrderItems() {

        List<OrderItemResponseDto>
                orderItemResponseDtoList =
                iOrderItemService
                        .getAllOrderItems();

        return new ResponseEntity<>(
                orderItemResponseDtoList,
                HttpStatus.OK);
    }

    @PutMapping("/orderItem/{id}")
    public ResponseEntity<OrderItemResponseDto>
    updateOrderItem(
            @PathVariable("id")
            Long orderItemId,

            @RequestBody
            OrderItemRequestDto orderItemRequestDto) {

        OrderItemResponseDto
                orderItemResponseDto =
                iOrderItemService
                        .updateOrderItem(
                                orderItemId,
                                orderItemRequestDto);

        return new ResponseEntity<>(
                orderItemResponseDto,
                HttpStatus.OK);
    }

    @DeleteMapping("/orderItem/{id}")
    public ResponseEntity<String>
    deleteOrderItem(
            @PathVariable("id")
            Long orderItemId) {

        String str =
                iOrderItemService
                        .deleteOrderItem(
                                orderItemId);

        return new ResponseEntity<>(
                str,
                HttpStatus.OK);
    }
}