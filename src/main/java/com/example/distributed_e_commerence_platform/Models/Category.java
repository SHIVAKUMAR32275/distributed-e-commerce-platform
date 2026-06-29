package com.example.distributed_e_commerence_platform.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseModel {

    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

}
