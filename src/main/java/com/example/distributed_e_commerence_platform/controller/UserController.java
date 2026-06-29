package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.UserRequestDto;
import com.example.distributed_e_commerence_platform.dtos.UserResponseDto;
import com.example.distributed_e_commerence_platform.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final IUserService iUserService;

    public UserController(
            IUserService iUserService) {

        this.iUserService =
                iUserService;
    }

    // Signup is already handled by AuthenticationController
    @PostMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto>
    createUser(
            @RequestBody
            UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto =
                iUserService
                        .createUser(
                                userRequestDto);

        return new ResponseEntity<>(
                userResponseDto,
                HttpStatus.CREATED);
    }

    // CUSTOMER can view own profile
    // ADMIN can view any profile
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<UserResponseDto>
    getUserDetails(
            @PathVariable("id")
            Long userId) {

        UserResponseDto userResponseDto =
                iUserService
                        .getUserDetails(
                                userId);

        return new ResponseEntity<>(
                userResponseDto,
                HttpStatus.OK);
    }

    // ADMIN only
    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>>
    getAllUserDetails() {

        List<UserResponseDto>
                userResponseDtoList =
                iUserService
                        .getAllUserDetails();

        return new ResponseEntity<>(
                userResponseDtoList,
                HttpStatus.OK);
    }

    // CUSTOMER updates own profile
    // ADMIN updates any profile
    @PutMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<UserResponseDto>
    updateUser(
            @PathVariable("id")
            Long userId,

            @RequestBody
            UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto =
                iUserService
                        .updateTheUser(
                                userId,
                                userRequestDto);

        return new ResponseEntity<>(
                userResponseDto,
                HttpStatus.OK);
    }

    // ADMIN only
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>
    deleteUser(
            @PathVariable("id")
            Long userId) {

        String str =
                iUserService
                        .deleteUserDetails(
                                userId);

        return new ResponseEntity<>(
                str,
                HttpStatus.OK);
    }
}