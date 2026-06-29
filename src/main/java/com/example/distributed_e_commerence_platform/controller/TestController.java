package com.example.distributed_e_commerence_platform.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Hello Admin";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customer() {
        return "Hello Customer";
    }
}