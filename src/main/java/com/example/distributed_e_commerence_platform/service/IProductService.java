package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.ProductRequestDto;
import com.example.distributed_e_commerence_platform.dtos.ProductResponseDto;

import java.util.List;

public interface IProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto getProduct( Long product_id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long product_id,ProductRequestDto productRequestDto);

    String deleteProduct(Long product_id);

}
