package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Address;
import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.Models.constants.Status;
import com.example.distributed_e_commerence_platform.dtos.AddressRequestDto;
import com.example.distributed_e_commerence_platform.dtos.AddressResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.AddressNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.UserNotFoundException;
import com.example.distributed_e_commerence_platform.repository.AddressRepo;
import com.example.distributed_e_commerence_platform.repository.UserRepo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService{

    private AddressRepo addressRepo;

    private UserRepo userRepo;

    public AddressServiceImpl( AddressRepo addressRepo , UserRepo userRepo ){
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }


    @Override
    public AddressResponseDto createAddress(AddressRequestDto addressRequestDto) {

        User user = userRepo.findById(addressRequestDto.getUserId()).orElseThrow(
                ()-> new UserNotFoundException(" User not Found with userId "+addressRequestDto.getUserId()));

        Address address = new Address();
        address.setUser(user);
        address.setCity(addressRequestDto.getCity());
        address.setHouseNo(addressRequestDto.getHouseNo());
        address.setStreetNo(addressRequestDto.getStreetNo());
        address.setState(addressRequestDto.getState());
        address.setPinCode(addressRequestDto.getPinCode());

        Address savedAddress = addressRepo.save(address);

        return savedAddress.convertToAddressResponse(savedAddress);
    }

    @Override
    public AddressResponseDto getAddress(Long addressId) {
        Address address = addressRepo.findById(addressId).orElseThrow(
                ()-> new AddressNotFoundException(" Address with id "+
                        addressId+" is not present ...............!")
        );

        return address.convertToAddressResponse(address);
    }

    @Override
    public List<AddressResponseDto> getAllAddressDetails() {
        List<Address> addresses = addressRepo.findAll();

        List<AddressResponseDto> addressResponseDtoList = new ArrayList<>();

        for( Address address : addresses ){
            addressResponseDtoList.add(address.convertToAddressResponse(address));
        }
        return addressResponseDtoList;
    }

    @Override
    public AddressResponseDto updateAddressDetails(Long addressId, AddressRequestDto addressRequestDto) {
        Address address = addressRepo.findById(addressId).orElseThrow(
                ()-> new AddressNotFoundException(" Address Not found ")
        );

        User user = userRepo.findById(addressRequestDto.getUserId()).orElseThrow(
                ()-> new UserNotFoundException( " User not found or not associated with address ")
        );

        address.setUser(user);
        address.setHouseNo(addressRequestDto.getHouseNo());
        address.setStreetNo(addressRequestDto.getStreetNo());
        address.setCity(addressRequestDto.getCity());
        address.setState(addressRequestDto.getState());
        address.setPinCode(addressRequestDto.getPinCode());

        Address savedAddress = addressRepo.save(address);

        return savedAddress.convertToAddressResponse(savedAddress);
    }

    @Override
    public String deleteAddress(Long addressId) {

        Address address = addressRepo.findById(addressId)
                .orElseThrow(() ->
                        new AddressNotFoundException(
                                "Address with id "
                                        + addressId
                                        + " is not present"));

        addressRepo.delete(address);

        return "Address Deleted Successfully";
    }


}
