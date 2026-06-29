package com.example.distributed_e_commerence_platform.repository;

import com.example.distributed_e_commerence_platform.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {
}
