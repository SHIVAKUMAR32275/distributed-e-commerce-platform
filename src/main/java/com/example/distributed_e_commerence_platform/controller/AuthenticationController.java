package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.Pojos.UserToken;
import com.example.distributed_e_commerence_platform.dtos.LoginRequestDto;
import com.example.distributed_e_commerence_platform.dtos.LogoutRequestDto;
import com.example.distributed_e_commerence_platform.dtos.SignUpRequestDto;
import com.example.distributed_e_commerence_platform.dtos.ValidationRequestDto;
import com.example.distributed_e_commerence_platform.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final IAuthService iAuthService;

    public AuthenticationController( IAuthService iAuthService ){
        this.iAuthService=iAuthService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<User> signUpUser(@RequestBody SignUpRequestDto signUpRequestDto ){
        User user = iAuthService.signUpUser(signUpRequestDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserToken> loginUser(@RequestBody LoginRequestDto loginRequestDto ){
        UserToken userToken = iAuthService.loginUser(loginRequestDto);
        return new ResponseEntity<>(userToken,HttpStatus.OK);
    }

    @PostMapping("/user/validate")
    public ResponseEntity<Boolean> validateUser(@RequestBody ValidationRequestDto validationRequestDto  ){
        Boolean valid = iAuthService.validateToken(validationRequestDto.getUserToken());
        return new ResponseEntity<>(valid,HttpStatus.OK);
    }

    @PostMapping("/user/logout")
    public ResponseEntity<Boolean> logoutUser(
            @RequestBody LogoutRequestDto requestDto){

        return ResponseEntity.ok(
                iAuthService.logoutUser(
                        requestDto.getUserToken()));
    }
}
