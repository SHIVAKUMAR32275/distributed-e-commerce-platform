package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.Pojos.UserToken;
import com.example.distributed_e_commerence_platform.dtos.LoginRequestDto;
import com.example.distributed_e_commerence_platform.dtos.SignUpRequestDto;

public interface IAuthService {

    User signUpUser(SignUpRequestDto signUpRequestDto );

    UserToken loginUser(LoginRequestDto loginRequestDto);

    boolean validateToken(String userToken);

    boolean logoutUser(String userToken);


}
