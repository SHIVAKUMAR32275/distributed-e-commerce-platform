package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Order;
import com.example.distributed_e_commerence_platform.Models.Payment;
import com.example.distributed_e_commerence_platform.Models.constants.PaymentMode;
import com.example.distributed_e_commerence_platform.Models.constants.PaymentStatus;
import com.example.distributed_e_commerence_platform.dtos.PaymentRequestDto;
import com.example.distributed_e_commerence_platform.dtos.PaymentResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.OrderNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.PaymentNotFoundException;
import com.example.distributed_e_commerence_platform.repository.OrderRepo;
import com.example.distributed_e_commerence_platform.repository.PaymentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepo paymentRepo;

    private final OrderRepo orderRepo;

    public PaymentServiceImpl( PaymentRepo paymentRepo , OrderRepo orderRepo ){
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
    }

    @Override
    public PaymentResponseDto createPaymentOrder(PaymentRequestDto paymentRequestDto) {
        Order order = orderRepo.findById(paymentRequestDto.getOrderId())
                .orElseThrow(()->
                        new OrderNotFoundException(" order Not found "));

        Payment payment = new Payment();
        payment.setPaymentDate(paymentRequestDto.getPaymentDate());
        payment.setPaymentMode(PaymentMode.CARD);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setTransactionId(paymentRequestDto.getTransactionId());
        payment.setOrder(order);

        Payment savedPayment = paymentRepo.save(payment);

        return savedPayment.convertToPaymentResposne(savedPayment);
    }

    @Override
    public PaymentResponseDto getPaymentDetails(Long paymentId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(()->
                        new PaymentNotFoundException( " Payment with id "+
                                paymentId+
                                " is Not found "));

        return payment.convertToPaymentResposne(payment);
    }

    @Override
    public List<PaymentResponseDto> getAllPaymentDetails() {
        List<Payment> paymentList = paymentRepo.findAll();

        List<PaymentResponseDto> paymentResponseDtos = new ArrayList<>();

        for( Payment payment : paymentList ){
            paymentResponseDtos.add(payment.convertToPaymentResposne(payment));
        }

        return paymentResponseDtos;
    }

    @Override
    public PaymentResponseDto updatePaymentDetails(Long paymentId, PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(()->
                        new PaymentNotFoundException(" Payment not found with" +
                                " id"+paymentId+" " +
                                "Enter a  valid id"));


        payment.setPaymentDate(paymentRequestDto.getPaymentDate());
        payment.setPaymentMode(paymentRequestDto.getPaymentMode());
        payment.setPaymentStatus(paymentRequestDto.getPaymentStatus());
        payment.setAmount(paymentRequestDto.getAmount());
        if( paymentRequestDto.getPaymentStatus().equals("SUCCESS")){
            payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        }
        if( paymentRequestDto.getPaymentStatus().equals("PENDING")){
            payment.setPaymentStatus(PaymentStatus.PENDING);
        }
        if( paymentRequestDto.getPaymentStatus().equals("REFUNDED")){
            payment.setPaymentStatus(PaymentStatus.REFUNDED);
        }

        if( paymentRequestDto.getPaymentMode().equals("CARD")){
            payment.setPaymentMode(PaymentMode.CARD);
        }
        if( paymentRequestDto.getPaymentMode().equals("CASH")){
            payment.setPaymentMode(PaymentMode.CASH);
        }
        if( paymentRequestDto.getPaymentMode().equals("NET_BANKING")){
            payment.setPaymentMode(PaymentMode.NET_BANKING);
        }
        payment.setTransactionId(paymentRequestDto.getTransactionId());

        Payment savedPayment = paymentRepo.save(payment);

        return savedPayment.convertToPaymentResposne(savedPayment);
    }

    @Override
    public String deletePaymentRecord(Long paymentId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(()->
                        new PaymentNotFoundException(" Payment not found with" +
                                " id"+paymentId+" " +
                                "Enter a  valid id"));

        paymentRepo.delete(payment);
        return " Payment record deleted successfully ................!!! ";
    }



}
