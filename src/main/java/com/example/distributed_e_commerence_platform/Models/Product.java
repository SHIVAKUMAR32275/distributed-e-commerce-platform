package com.example.distributed_e_commerence_platform.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseModel {

    private String name;

    private String description;

    private String brand;

    private BigDecimal price;

    private String imageUrl;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToOne(mappedBy = "product")
    private Inventory inventory;
}