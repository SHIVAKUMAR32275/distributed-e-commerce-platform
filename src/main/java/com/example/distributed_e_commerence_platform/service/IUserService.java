package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.UserRequestDto;
import com.example.distributed_e_commerence_platform.dtos.UserResponseDto;

import java.util.List;

public interface IUserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserDetails(Long userId );

    List<UserResponseDto> getAllUserDetails();

    UserResponseDto updateTheUser(Long userId , UserRequestDto userRequestDto);

    String deleteUserDetails(Long userId );

}
