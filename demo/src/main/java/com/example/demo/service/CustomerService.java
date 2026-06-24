package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }
    public List<Customer> findAll() { return customerRepository.findAll(); }
    public Customer add(Customer customer) {
        if (customer.getStatus() == null) customer.setStatus("NORMAL");
        return customerRepository.save(customer);
    }
}
