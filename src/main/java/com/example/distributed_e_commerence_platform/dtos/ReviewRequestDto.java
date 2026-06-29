package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {


    private int rating;

    private String comment;

    private Long userId;

    private Long productId;

}
