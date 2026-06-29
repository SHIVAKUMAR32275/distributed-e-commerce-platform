package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.Models.constants.PaymentMode;
import com.example.distributed_e_commerence_platform.Models.constants.PaymentStatus;
import com.example.distributed_e_commerence_platform.dtos.PaymentResponseDto;
import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;

    private String transactionId;

    @OneToOne
    private Order order;

    public PaymentResponseDto convertToPaymentResposne( Payment savedPayment){
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();

        paymentResponseDto.setPaymentMode(savedPayment.getPaymentMode());
        paymentResponseDto.setPaymentDate(savedPayment.getPaymentDate());
        paymentResponseDto.setAmount(savedPayment.getAmount());
        paymentResponseDto.setTransactionId(savedPayment.getTransactionId());
        paymentResponseDto.setPaymentStatus(savedPayment.getPaymentStatus());
        paymentResponseDto.setPaymentStatus(savedPayment.getPaymentStatus());
        paymentResponseDto.setPaymentMode(savedPayment.getPaymentMode());

        if( savedPayment.getOrder() != null ){
            paymentResponseDto.setOrderId(savedPayment.getOrder().getId());
        }
        return paymentResponseDto;
    }


}
