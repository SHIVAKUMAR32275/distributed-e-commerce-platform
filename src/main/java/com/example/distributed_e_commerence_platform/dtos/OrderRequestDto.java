package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderRequestDto {

    private Date orderedDate;

    private String orderNumber;

    private BigDecimal totalAmount;

    private Long userId;

    private Long addressId;

}
