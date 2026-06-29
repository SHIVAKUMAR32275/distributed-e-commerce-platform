package com.example.distributed_e_commerence_platform.repository;

import com.example.distributed_e_commerence_platform.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {

}
