package com.example.distributed_e_commerence_platform.dtos;

import com.example.distributed_e_commerence_platform.Models.Category;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {
    private String name;

    private String description;

    private String brand;

    private BigDecimal price;

    private String imageUrl;

    private Long categoryId;
}
