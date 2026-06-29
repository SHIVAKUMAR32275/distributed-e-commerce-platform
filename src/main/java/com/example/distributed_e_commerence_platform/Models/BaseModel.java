package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.Models.constants.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

//    @Enumerated(EnumType.STRING)
//    private Status status;

}
