package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.AddressRequestDto;
import com.example.distributed_e_commerence_platform.dtos.AddressResponseDto;
import com.example.distributed_e_commerence_platform.service.IAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('CUSTOMER')")
public class AddressController {

    private final IAddressService iAddressService;

    public AddressController(
            IAddressService iAddressService) {

        this.iAddressService =
                iAddressService;
    }

    @PostMapping("/address")
    public ResponseEntity<AddressResponseDto>
    createAddress(
            @RequestBody
            AddressRequestDto addressRequestDto) {

        AddressResponseDto addressResponseDto =
                iAddressService
                        .createAddress(
                                addressRequestDto);

        return new ResponseEntity<>(
                addressResponseDto,
                HttpStatus.CREATED);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressResponseDto>
    getAddressDetails(
            @PathVariable("id")
            Long addressId) {

        AddressResponseDto addressResponseDto =
                iAddressService
                        .getAddress(
                                addressId);

        return new ResponseEntity<>(
                addressResponseDto,
                HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressResponseDto>>
    getAllAddressDetails() {

        List<AddressResponseDto>
                addressResponseDto =
                iAddressService
                        .getAllAddressDetails();

        return new ResponseEntity<>(
                addressResponseDto,
                HttpStatus.OK);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<AddressResponseDto>
    updateAddressDetails(
            @PathVariable("id")
            Long addressId,

            @RequestBody
            AddressRequestDto addressRequestDto) {

        AddressResponseDto addressResponseDto =
                iAddressService
                        .updateAddressDetails(
                                addressId,
                                addressRequestDto);

        return new ResponseEntity<>(
                addressResponseDto,
                HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<String>
    deleteAddress(
            @PathVariable("id")
            Long addressId) {

        String response =
                iAddressService
                        .deleteAddress(
                                addressId);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }
}