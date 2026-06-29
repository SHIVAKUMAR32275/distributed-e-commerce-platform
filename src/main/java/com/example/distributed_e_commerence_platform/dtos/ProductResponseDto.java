package com.example.distributed_e_commerence_platform.dtos;

import com.example.distributed_e_commerence_platform.Models.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class ProductResponseDto {

    private Long product_id;

    private String name;

    private String description;

    private String brand;

    private BigDecimal price;

    private String imageUrl;

    private Long categoryId;

    private String categoryName;


//    public ProductResponseDto convertToProductResponse( Product product){
//        ProductResponseDto responseDto = new ProductResponseDto();
//
//        responseDto.setName(product.getName());
//        responseDto.setPrice(product.getPrice());
//        responseDto.setBrand(product.getBrand());
//        responseDto.setDescription(product.getDescription());
//        responseDto.setCategoryId(product.getCategory().getId());
//        responseDto.setCategoryName(product.getCategory().getName());
//        responseDto.setImageUrl(product.getImageUrl());
//
//        return responseDto;
//    }


}
