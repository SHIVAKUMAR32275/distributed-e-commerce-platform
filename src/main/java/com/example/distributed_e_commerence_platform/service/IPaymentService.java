package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.PaymentRequestDto;
import com.example.distributed_e_commerence_platform.dtos.PaymentResponseDto;

import java.util.List;

public interface IPaymentService {

    PaymentResponseDto createPaymentOrder(PaymentRequestDto paymentRequestDto);

    PaymentResponseDto getPaymentDetails( Long paymentId );

    List<PaymentResponseDto> getAllPaymentDetails();

    PaymentResponseDto updatePaymentDetails(Long paymentId,PaymentRequestDto paymentRequestDto);

    String deletePaymentRecord( Long paymentId);

}
