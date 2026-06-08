package com.example.demo.customer;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerServiceController {

    @GetMapping("/hello")
    public String hello() {
        return "Customer Service Module Running";
    }
}
