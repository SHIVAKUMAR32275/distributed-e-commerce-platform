package com.example.distributed_e_commerence_platform.dtos;

import com.example.distributed_e_commerence_platform.Models.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {

    @NotBlank
    private String categoryName;

    @NotBlank
    private String description;


    public CategoryResponseDto convertToResponse(Category category){
        CategoryResponseDto categoryResponseDto1 = new CategoryResponseDto();
        categoryResponseDto1.setCategoryName(category.getName());
        categoryResponseDto1.setDescription(category.getDescription());
        categoryResponseDto1.setId(category.getId());

        return categoryResponseDto1;
    }


}
