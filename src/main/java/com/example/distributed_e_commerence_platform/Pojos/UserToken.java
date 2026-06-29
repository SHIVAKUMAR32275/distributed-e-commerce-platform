package com.example.distributed_e_commerence_platform.Pojos;

import com.example.distributed_e_commerence_platform.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

    private String userToken;

    private User user;


}
