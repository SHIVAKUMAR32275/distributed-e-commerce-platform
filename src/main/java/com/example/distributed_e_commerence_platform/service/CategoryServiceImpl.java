package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Category;
import com.example.distributed_e_commerence_platform.dtos.CategoryRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CategoryResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.CategoryNotFoundException;
import com.example.distributed_e_commerence_platform.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService{

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {

        Category category = new Category();
        category.setName(categoryRequestDto.getCategoryName());
        category.setDescription(categoryRequestDto.getDescription());

        Category savedCategory = categoryRepo.save(category);

        return categoryRequestDto.convertToResponse( savedCategory);
    }

    @Override
    public CategoryResponseDto getCategory(Long category_id) {
        Category category = categoryRepo.findById(category_id)
                .orElseThrow(()-> new CategoryNotFoundException("Category with id "+category_id+" is not present in DB"));

            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setCategoryName(category.getName());
            categoryResponseDto.setDescription(category.getDescription());
            categoryResponseDto.setId(category.getId());

        return categoryResponseDto;
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {

        List<Category> categoryList = categoryRepo.findAll();

        List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();

        for( Category category : categoryList ){
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setCategoryName(category.getName());
            categoryResponseDto.setDescription(category.getDescription());
            categoryResponseDto.setId(category.getId());

            categoryResponseDtoList.add(categoryResponseDto);
        }

        return categoryResponseDtoList;
    }

    @Override
    public CategoryResponseDto updateCategory(Long category_id, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepo.findById(category_id)
                .orElseThrow(()-> new CategoryNotFoundException(" category with id + "+category_id+" is not found in DB"));

        category.setDescription(categoryRequestDto.getDescription());
        category.setName(categoryRequestDto.getCategoryName());

        Category updatedCategory = categoryRepo.save(category);

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(updatedCategory.getId());
        categoryResponseDto.setCategoryName(updatedCategory.getName());
        categoryResponseDto.setDescription(updatedCategory.getDescription());

        return categoryResponseDto;
    }

    @Override
    public void deleteCategory(Long category_id) {
        Category category = categoryRepo.findById(category_id)
                .orElseThrow(()-> new CategoryNotFoundException(" category not found "));

        categoryRepo.delete(category);

    }
}
