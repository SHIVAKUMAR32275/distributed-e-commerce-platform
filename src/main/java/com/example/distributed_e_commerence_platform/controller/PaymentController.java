package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.PaymentRequestDto;
import com.example.distributed_e_commerence_platform.dtos.PaymentResponseDto;
import com.example.distributed_e_commerence_platform.service.IPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    private final IPaymentService iPaymentService;

    public PaymentController(
            IPaymentService iPaymentService) {

        this.iPaymentService =
                iPaymentService;
    }

    // CUSTOMER initiates payment
    @PostMapping("/payment")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<PaymentResponseDto>
    createPaymentRecord(
            @RequestBody
            PaymentRequestDto paymentRequestDto) {

        PaymentResponseDto responseDto =
                iPaymentService
                        .createPaymentOrder(
                                paymentRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.CREATED);
    }

    // CUSTOMER and ADMIN can view payment
    @GetMapping("/payment/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<PaymentResponseDto>
    getPaymentRecord(
            @PathVariable("id")
            Long paymentId) {

        PaymentResponseDto responseDto =
                iPaymentService
                        .getPaymentDetails(
                                paymentId);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK);
    }

    // ADMIN only
    @GetMapping("/payment")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PaymentResponseDto>>
    getAllPaymentRecords() {

        List<PaymentResponseDto>
                responseDtoList =
                iPaymentService
                        .getAllPaymentDetails();

        return new ResponseEntity<>(
                responseDtoList,
                HttpStatus.OK);
    }

    // ADMIN updates payment status
    @PutMapping("/payment/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaymentResponseDto>
    updatePaymentRecord(
            @PathVariable("id")
            Long paymentId,

            @RequestBody
            PaymentRequestDto paymentRequestDto) {

        PaymentResponseDto responseDto =
                iPaymentService
                        .updatePaymentDetails(
                                paymentId,
                                paymentRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK);
    }

    // ADMIN deletes payment records
    @DeleteMapping("/payment/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>
    deletePaymentRecord(
            @PathVariable("id")
            Long paymentId) {

        String str =
                iPaymentService
                        .deletePaymentRecord(
                                paymentId);

        return new ResponseEntity<>(
                str,
                HttpStatus.OK);
    }
}