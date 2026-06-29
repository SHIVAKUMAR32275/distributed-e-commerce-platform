package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.CategoryRequestDto;
import com.example.distributed_e_commerence_platform.dtos.CategoryResponseDto;
import com.example.distributed_e_commerence_platform.service.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final ICategoryService iCategoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @PostMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDto> createACategory(
            @RequestBody CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto responseDto =
                iCategoryService.createCategory(categoryRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CategoryResponseDto> getIndividualCategoryDetails(
            @PathVariable("id") Long category_id) {

        CategoryResponseDto categoryResponseDto =
                iCategoryService.getCategory(category_id);

        return new ResponseEntity<>(
                categoryResponseDto,
                HttpStatus.OK);
    }

    @GetMapping("/category")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategoryDetails() {

        List<CategoryResponseDto> categoryResponseDtoList =
                iCategoryService.getAllCategories();

        return new ResponseEntity<>(
                categoryResponseDtoList,
                HttpStatus.OK);
    }

    @PutMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable("id") Long category_id,
            @RequestBody CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto responseDto =
                iCategoryService.updateCategory(
                        category_id,
                        categoryRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable("id") Long category_id) {

        iCategoryService.deleteCategory(category_id);

        return ResponseEntity.noContent().build();
    }
}