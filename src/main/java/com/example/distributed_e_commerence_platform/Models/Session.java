package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.Models.constants.SessionStatus;
import com.example.distributed_e_commerence_platform.Models.constants.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session extends BaseModel{



    @Column(length = 500)
    private String userToken;

    private String userName;

    @Enumerated(EnumType.STRING)
    private SessionStatus SessionStatus;

}
