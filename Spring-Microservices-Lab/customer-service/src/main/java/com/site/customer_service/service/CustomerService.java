package com.site.customerservice.service;

import com.site.customerservice.model.Customer;
import com.site.customerservice.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Save customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get customer by ID
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Update customer
    public Customer updateCustomer(String id, Customer updatedCustomer) {

        Customer existingCustomer = getCustomerById(id);

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    // Delete customer
    public void deleteCustomer(String id) {

        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }

        customerRepository.deleteById(id);
    }
}