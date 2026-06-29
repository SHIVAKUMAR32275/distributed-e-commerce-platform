package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Category;
import com.example.distributed_e_commerence_platform.Models.Product;
import com.example.distributed_e_commerence_platform.dtos.ProductRequestDto;
import com.example.distributed_e_commerence_platform.dtos.ProductResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.CategoryNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.ProductNotFoundException;
import com.example.distributed_e_commerence_platform.repository.CategoryRepo;
import com.example.distributed_e_commerence_platform.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo , CategoryRepo categoryRepo ){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Category category = categoryRepo.findById(productRequestDto.getCategoryId())
                .orElseThrow(()->new CategoryNotFoundException(" Category Not found "));

        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setBrand(productRequestDto.getBrand());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(category);

        Product product1 = productRepo.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProduct_id(product1.getId());
        productResponseDto.setBrand(product1.getBrand());
        productResponseDto.setImageUrl(product1.getImageUrl());
        productResponseDto.setDescription(product1.getDescription());
        productResponseDto.setName(product1.getName());
        productResponseDto.setCategoryId(product1.getCategory().getId());
        productResponseDto.setCategoryName(product1.getCategory().getName());
        productResponseDto.setPrice(product1.getPrice());


        return productResponseDto;
    }

    @Override
    public ProductResponseDto getProduct(Long product_id) {
        Product product = productRepo.findById(product_id)
                .orElseThrow(()-> new ProductNotFoundException(" Product with id "+product_id+ " is not found"));

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setBrand(product.getBrand());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setCategoryId(product.getCategory().getId());
        productResponseDto.setCategoryName(product.getCategory().getName());
        productResponseDto.setName(product.getName());


        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> responseDtoList = productRepo.findAll();

        List<ProductResponseDto> productResponseDtoArrayList = new ArrayList<>();

        for( Product product : responseDtoList )
        {
            ProductResponseDto responseDto = new ProductResponseDto();

            responseDto.setProduct_id(product.getId());
            responseDto.setName(product.getName());
            responseDto.setPrice(product.getPrice());
            responseDto.setBrand(product.getBrand());
            responseDto.setDescription(product.getDescription());
            responseDto.setCategoryId(product.getCategory().getId());
            responseDto.setCategoryName(product.getCategory().getName());
            responseDto.setImageUrl(product.getImageUrl());

            productResponseDtoArrayList.add(responseDto);
        }

        return productResponseDtoArrayList;
    }

    @Override
    public ProductResponseDto updateProduct(
            Long product_id,
            ProductRequestDto productRequestDto) {

        Product product = productRepo.findById(product_id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with id "
                                        + product_id
                                        + " is not found. Enter a valid id"));

        Category category = categoryRepo.findById(
                        productRequestDto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category with id "
                                        + productRequestDto.getCategoryId()
                                        + " is not found"));

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setBrand(productRequestDto.getBrand());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setCategory(category);

        Product savedProduct = productRepo.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setProduct_id(savedProduct.getId());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setDescription(savedProduct.getDescription());
        productResponseDto.setBrand(savedProduct.getBrand());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setImageUrl(savedProduct.getImageUrl());
        productResponseDto.setCategoryId(savedProduct.getCategory().getId());
        productResponseDto.setCategoryName(savedProduct.getCategory().getName());

        return productResponseDto;
    }
    @Override
    public String deleteProduct(Long product_id) {
        Product product = productRepo.findById(product_id)
                .orElseThrow(()->new ProductNotFoundException(" Product with id "+product_id+" doesn't exist in DB"));

        productRepo.delete(product);
        return " Product Deleted Successfully";
    }

}
