package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.dtos.AddressRequestDto;
import com.example.distributed_e_commerence_platform.dtos.AddressResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel{

    private String houseNo;

    private String streetNo;

    private String city;

    private String state;

    private String pinCode;

    @ManyToOne
    private User user;


    public AddressResponseDto convertToAddressResponse(Address savedAddress){
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        addressResponseDto.setHouseNo(savedAddress.getHouseNo());
        addressResponseDto.setStreetNo(savedAddress.getStreetNo());
        addressResponseDto.setCity(savedAddress.getCity());
        addressResponseDto.setPinCode(savedAddress.getPinCode());
        addressResponseDto.setAddressId(savedAddress.getId());
        if( savedAddress.getUser() != null ){
            addressResponseDto.setUserId(savedAddress.getUser().getId());
            addressResponseDto.setUserName(savedAddress.getUser().getFirstName());
        }

        addressResponseDto.setState(savedAddress.getState());


        return addressResponseDto;
    }






}
