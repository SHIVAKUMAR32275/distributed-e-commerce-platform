package com.example.distributed_e_commerence_platform.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends BaseModel {


    private String roleName;

}
