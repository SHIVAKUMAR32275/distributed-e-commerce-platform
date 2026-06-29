package com.example.distributed_e_commerence_platform.dtos;

import com.example.distributed_e_commerence_platform.Models.constants.PaymentMode;
import com.example.distributed_e_commerence_platform.Models.constants.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

public class PaymentRequestDto {

    private Long paymentId;

    private LocalDateTime paymentDate;

    private String transactionId;

    private BigDecimal amount;

    private Long orderId;

    private PaymentStatus paymentStatus;

    private PaymentMode paymentMode;

}
