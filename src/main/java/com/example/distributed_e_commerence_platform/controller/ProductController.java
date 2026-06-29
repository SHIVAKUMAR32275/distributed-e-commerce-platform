package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.ProductRequestDto;
import com.example.distributed_e_commerence_platform.dtos.ProductResponseDto;
import com.example.distributed_e_commerence_platform.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> createAProduct(
            @RequestBody ProductRequestDto productRequestDto) {

        ProductResponseDto responseDto =
                iProductService.createProduct(productRequestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductResponseDto> getProductDetails(
            @PathVariable("id") Long product_id) {

        ProductResponseDto responseDto =
                iProductService.getProduct(product_id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/product")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductResponseDto>> getAllProductDetails() {

        List<ProductResponseDto> response =
                iProductService.getAllProducts();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> updateProductDetails(
            @PathVariable("id") Long produc_id,
            @RequestBody ProductRequestDto productRequestDto) {

        ProductResponseDto productResponseDto =
                iProductService.updateProduct(
                        produc_id,
                        productRequestDto);

        return new ResponseEntity<>(
                productResponseDto,
                HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProductById(
            @PathVariable("id") Long product_id) {

        String str =
                iProductService.deleteProduct(product_id);

        return new ResponseEntity<>(
                str,
                HttpStatus.OK);
    }
}