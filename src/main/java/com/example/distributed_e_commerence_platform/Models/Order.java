package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.Models.constants.OrderStatus;
import com.example.distributed_e_commerence_platform.dtos.OrderResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseModel {

    private Date orderedDate;

    private String orderNumber;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;


    public OrderResponseDto convertToOrderResponse( Order order ){
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setOrderNumber(order.getOrderNumber());
        orderResponseDto.setOrderedDate(order.getOrderedDate());
        orderResponseDto.setTotalAmount(order.getTotalAmount());
        if( order.getUser() != null ){
            orderResponseDto.setUserId(order.getUser().getId());
        }
        orderResponseDto.setUserName(order.getUser().getFirstName());
        return orderResponseDto;

    }

}
