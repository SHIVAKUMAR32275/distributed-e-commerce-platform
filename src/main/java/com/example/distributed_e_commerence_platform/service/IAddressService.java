package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.AddressRequestDto;
import com.example.distributed_e_commerence_platform.dtos.AddressResponseDto;

import java.util.List;

public interface IAddressService {

    AddressResponseDto createAddress(AddressRequestDto addressRequestDto);

    AddressResponseDto getAddress(Long addressId);

    List<AddressResponseDto> getAllAddressDetails();

    AddressResponseDto updateAddressDetails(Long addressId , AddressRequestDto addressRequestDto);

    String deleteAddress(Long addressId);

}


