package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Address;
import com.example.distributed_e_commerence_platform.Models.Order;
import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.Models.constants.OrderStatus;
import com.example.distributed_e_commerence_platform.dtos.OrderRequestDto;
import com.example.distributed_e_commerence_platform.dtos.OrderResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.AddressNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.OrderNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.UserNotFoundException;
import com.example.distributed_e_commerence_platform.repository.AddressRepo;
import com.example.distributed_e_commerence_platform.repository.OrderRepo;
import com.example.distributed_e_commerence_platform.repository.UserRepo;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepo orderRepo;

    private final UserRepo userRepo;

    private final AddressRepo addressRepo;

    public OrderServiceImpl(OrderRepo orderRepo , UserRepo userRepo ,AddressRepo addressRepo){
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }


    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        User user = userRepo.findById(orderRequestDto.getUserId())
                .orElseThrow(
                        ()->new UserNotFoundException(" User not Found ")
                );


        Address address = addressRepo.findById(orderRequestDto.getAddressId())
                .orElseThrow(
                        ()-> new AddressNotFoundException( " Address Not Found ............!!!!! ")
                );

        Order order =  new Order();

        order.setOrderedDate(orderRequestDto.getOrderedDate());
        order.setOrderNumber(orderRequestDto.getOrderNumber());
        order.setTotalAmount(orderRequestDto.getTotalAmount());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setUser(user);

        Order savedOrder = orderRepo.save(order);

        return order.convertToOrderResponse(savedOrder);
    }

    @Override
    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(
                        ()-> new OrderNotFoundException(" order not found with orderId "+orderId)
                );
        return order.convertToOrderResponse(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrder() {
        List<Order> orderList = orderRepo.findAll();

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for( Order order : orderList ){
            orderResponseDtoList.add(order.convertToOrderResponse(order));
        }

        return orderResponseDtoList;
    }

    @Override
    public OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(
                        ()->new OrderNotFoundException(" Order was not found ............!!! ")
                );

        order.setOrderedDate(orderRequestDto.getOrderedDate());
        order.setOrderNumber(orderRequestDto.getOrderNumber());
        order.setTotalAmount(orderRequestDto.getTotalAmount());

        Order savedOrder = orderRepo.save(order);

        return order.convertToOrderResponse(savedOrder);
    }

    @Override
    public String deleteOrder(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(
                        ()-> new OrderNotFoundException(
                                " Order not found with orderId "+orderId
                        )
                );

        orderRepo.delete(order);
        return " Order deleted SuccessFully ............!! ";
    }


}
