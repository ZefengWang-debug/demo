package com.example.demo.controller;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service) { this.service = service; }
    @GetMapping("/list") public List<Customer> list() { return service.findAll(); }
    @PostMapping("/add") public Customer add(@RequestBody Customer customer) { return service.add(customer); }
}
