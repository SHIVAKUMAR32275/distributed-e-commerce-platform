package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.CategoryRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CategoryResponseDto;

import java.util.List;

public interface ICategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto getCategory( Long category_id);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Long category_id , CategoryRequestDto categoryRequestDto);

    void deleteCategory(Long category_id);



}
