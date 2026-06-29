package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Order;
import com.example.distributed_e_commerence_platform.Models.OrderItem;
import com.example.distributed_e_commerence_platform.Models.Product;
import com.example.distributed_e_commerence_platform.dtos.OrderItemRequestDto;
import com.example.distributed_e_commerence_platform.dtos.OrderItemResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.OrderNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.ProductNotFoundException;
import com.example.distributed_e_commerence_platform.repository.OrderItemRepo;
import com.example.distributed_e_commerence_platform.repository.OrderRepo;
import com.example.distributed_e_commerence_platform.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements  IOrderItemService {

    private final OrderItemRepo orderItemRepo;

    private final ProductRepo productRepo;

    private final OrderRepo orderRepo;

    public OrderItemServiceImpl( OrderItemRepo orderItemRepo , OrderRepo orderRepo , ProductRepo productRepo ){
        this.orderItemRepo = orderItemRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @Override
    public OrderItemResponseDto createOrderItem(OrderItemRequestDto orderItemRequestDto) {

        Product product = productRepo.findById(orderItemRequestDto.getProductId())
                .orElseThrow(
                        ()-> new ProductNotFoundException(" product not found with id " +
                                orderItemRequestDto.getProductId()+" enter a valid id ")
                );

        Order order = orderRepo.findById(orderItemRequestDto.getOrderId())
                .orElseThrow(
                        ()-> new OrderNotFoundException( " Order Not Found !! ")
                );


        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemRequestDto.getQuantity());
        orderItem.setPriceAtPurchase(orderItemRequestDto.getPriceAtPurchase());
        orderItem.setOrder(order);
        orderItem.setProduct(product);

        OrderItem savedOrderItem = orderItemRepo.save(orderItem);

        return savedOrderItem.convertToOrderItemResponse(savedOrderItem);
    }

    @Override
    public OrderItemResponseDto getOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow(()->
                        new OrderNotFoundException(" order item not found ")
                );

        return orderItem.convertToOrderItemResponse(orderItem);
    }

    @Override
    public List<OrderItemResponseDto> getAllOrderItems() {
        List<OrderItem> orderItemList = orderItemRepo.findAll();

        List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();

        for( OrderItem orderItem : orderItemList ){
            orderItemResponseDtoList.add(orderItem.convertToOrderItemResponse(orderItem));
        }
        return orderItemResponseDtoList;
    }

    @Override
    public OrderItemResponseDto updateOrderItem(Long orderItemId, OrderItemRequestDto orderItemRequestDto) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow(
                        ()-> new OrderNotFoundException(" Order Item Not Found ............!! ")
                );

        orderItem.setQuantity(orderItemRequestDto.getQuantity());
        orderItem.setPriceAtPurchase(orderItemRequestDto.getPriceAtPurchase());

        OrderItem savedOrderItem = orderItemRepo.save(orderItem);

        return savedOrderItem.convertToOrderItemResponse(savedOrderItem);
    }

    @Override
    public String deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow(
                        ()-> new OrderNotFoundException(
                                " order item not found enter a valid id "
                        )
                );

        orderItemRepo.delete(orderItem);
        return "Order Item Deleted successfully ....................!!! ";
    }
}
