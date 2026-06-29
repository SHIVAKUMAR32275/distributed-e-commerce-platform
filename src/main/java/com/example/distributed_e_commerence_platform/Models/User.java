package com.example.distributed_e_commerence_platform.Models;


import com.example.distributed_e_commerence_platform.Models.constants.Status;
import com.example.distributed_e_commerence_platform.dtos.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "AmazonUser")
public class User extends BaseModel {

    private String firstName;

    private String lastName;

    @NotBlank
    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Status userStatus;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;


    public UserResponseDto convertUserResponse( User savedUser ){
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setLastName(savedUser.getLastName());
        userResponseDto.setFirstName(savedUser.getFirstName());
        userResponseDto.setPhoneNumber(savedUser.getPhoneNumber());
        if(savedUser.getRole() != null){
            userResponseDto.setRoleId(savedUser.getRole().getId());
            userResponseDto.setRoleName(savedUser.getRole().getRoleName());
        }
        return userResponseDto;

    }

}
